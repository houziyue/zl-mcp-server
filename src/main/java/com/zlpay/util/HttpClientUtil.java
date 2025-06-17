/**   
 * Copyright © 2018 zlpay.
 */
package com.zlpay.util;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.zlpay.bo.gateway.MessageBody;
import com.zlpay.bo.gateway.MessageHeader;
import com.zlpay.constant.Constant;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 阿帕奇的httpclient
 * @author: syuf
 * @date: 2018年10月16日 下午2:53:50
 */
@Slf4j
public class HttpClientUtil {

	/**
	 * 连接超时时间
	 */
	private final static int CONNECT_TIMEOUT = 30000;
	/**
	 * 请求超时时间
	 */
	private final static int REQUEST_TIMEOUT = 50000;
	/**
	 * socket超时时间
	 */
	private final static int SOCKET_TIMEOUT = 50000;
	/**
	 * 请求格式
	 */
	private final static String FORMAT = "application/json";

	/**
	 * @Description: post请求
	 * @param url-请求地址
	 * @param header
	 *            RequestHeader对象
	 * @param jsonData
	 * @return String
	 * @throws @author:syuf
	 * @date: 2018年10月19日 下午5:13:32
	 */
	public static MessageBody doPost(String url, MessageHeader messageHeader, String jsonData) {
		// 创建可关闭client
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT)
				.setConnectionRequestTimeout(REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setRedirectsEnabled(true)
				.build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json");
		if (messageHeader != null) {
			httpPost.addHeader("msgId", messageHeader.getMsgId());
			httpPost.addHeader("merchNo", messageHeader.getMerchNo());
			httpPost.addHeader("txCode", messageHeader.getTxCode());
			httpPost.addHeader("version", messageHeader.getVersion());
			httpPost.addHeader("signNo", messageHeader.getSignNo());
			httpPost.addHeader("encrp", messageHeader.getEncrp());
			httpPost.addHeader("encrpNo", messageHeader.getEncrpNo());
			httpPost.addHeader("timestamp", messageHeader.getTimestamp());
			httpPost.addHeader("signa", messageHeader.getSigna());
		}
		try {
			httpPost.setEntity(new StringEntity(jsonData, ContentType.create(FORMAT, Constant.UTF8)));
			HttpResponse response = httpClient.execute(httpPost);
			HeaderIterator iterator = response.headerIterator();
			log.info("------------应答Header内容开始---------------");
			while(iterator.hasNext()) {
				Header nextHeader = iterator.nextHeader();
		        String param = nextHeader.getName();
		        String value = nextHeader.getValue();
		        log.info(param+" = "+value+";");
		        if("encrp".equals(param)) {
		        	messageHeader.setEncrp(value);
		        }
			}
			log.info("------------应答Header内容结束---------------");
			
			log.info("[httpClientUtil]收到应答内容:{}!!", response.getStatusLine().toString());
			if (response.getStatusLine().getStatusCode() == 200) {
				String resStr =  String.valueOf(EntityUtils.toString(response.getEntity()));
				log.info("[httpClientUtil]收到应答内容:{}!!", resStr);
				MessageBody respBody = JSON.parseObject(resStr,MessageBody.class);
				return respBody;
				
			}
		} catch (Exception e) {
			log.error("[httpClientUtil]请求异常!!", e);
		} finally {
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error("[httpClientUtil]流关闭异常!!", e);
				}
			}
		}
		return null;
	}
	
	

}
