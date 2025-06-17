package com.zlpay.service.impl;

import java.net.URLEncoder;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zlpay.assist.encrypt.sm4.SM4Util;
import com.zlpay.assist.sign.sm2.SM2Cert;
import com.zlpay.bo.CreateOrderBO;
import com.zlpay.bo.gateway.MessageBody;
import com.zlpay.bo.gateway.MessageHeader;
import com.zlpay.bo.gateway.MessageResponse;
import com.zlpay.bo.mall.QueryOrderStateReqBO;
import com.zlpay.bo.mall.QueryOrderStateRespBO;
import com.zlpay.bo.mall.RefundReqBO;
import com.zlpay.bo.mall.RefundRespBO;
import com.zlpay.bo.mall.TradeTerminalBO;
import com.zlpay.bo.payacc.BaseResBO;
import com.zlpay.bo.payacc.PayeeInfo;
import com.zlpay.bo.payacc.T0008ReqBO;
import com.zlpay.bo.payacc.T0008ResBO;
import com.zlpay.constant.Constant;
import com.zlpay.enums.TerminalEnum;
import com.zlpay.enums.mall.BusinessCodeEnum;
import com.zlpay.enums.mall.MallRespCodeEnum;
import com.zlpay.enums.mall.QueryTradeType;
import com.zlpay.enums.payacc.AccessTypeEnum;
import com.zlpay.enums.payacc.PayAccRespCodeEnum;
import com.zlpay.enums.payacc.SubAccessTypeEnum;
import com.zlpay.property.SystemProperty;
import com.zlpay.service.CashierService;
import com.zlpay.util.HttpClientUtil;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CashierServiceImpl implements CashierService {
	
	private  DateFormat df = new  SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	private SystemProperty systemProperty;
	//商户号
	private String merchantNo ;
	//商户私钥
	private String privateKey ;
	//商户私钥序列号
	private String serialNo ;
	//证联公钥
	private String publicKey ;
	//证联公钥序列号
	private String zlSerialNo ;
	
	@Autowired
	private Environment env;
	@PostConstruct
	public void init() {
		try {
			log.debug("[mcp-初始化]收银台对外接口开始");
			merchantNo = System.getenv(Constant.AP_APP_ID);
			//商户私钥
			privateKey = System.getenv(Constant.AP_APP_KEY);
			//商户私钥序列号
			serialNo = System.getenv(Constant.AP_APP_SERIAL_NO);
			//证联公钥
			publicKey = System.getenv(Constant.AP_PUB_KEY);
			//log.debug("[mcp-初始化]收银台入参merchantNo[{}],serialNo[{}],privateKey[{}],publicKey[{}]",merchantNo,serialNo,privateKey,publicKey);
			if(StringUtils.isBlank(merchantNo)) {
				merchantNo = env.getProperty(Constant.AP_APP_ID);
				//商户私钥
				privateKey = env.getProperty(Constant.AP_APP_KEY);
				//商户私钥序列号
				serialNo = env.getProperty(Constant.AP_APP_SERIAL_NO);
				//证联公钥
				publicKey = env.getProperty(Constant.AP_PUB_KEY);
				//log.debug("[mcp-初始化]收银台入参merchantNo[{}],serialNo[{}],privateKey[{}],publicKey[{}]",merchantNo,serialNo,privateKey,publicKey);
			}
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			byte[] publicByte = Base64.decode(publicKey);
			X509Certificate publicCert = SM2Cert.getPublicCert(publicByte);
			zlSerialNo = publicCert.getSerialNumber().toString(16);
			log.debug("[mcp-初始化]环境变量merchantNo[{}],zlSerialNo[{}]",merchantNo,zlSerialNo);
		} catch (Exception e) {
			log.error("[mcp-初始化]执行失败",e);
		}
	}
	
	@Override
	@Tool(name = "createWebPagePayment", description = "创建一笔付款订单，返回带有支付链接的 Markdown 文本，该链接在电脑浏览器中打开后会展示支付二维码，用户可扫码支付。本工具适用于桌面网站或电脑客户端。")
	public String createWebPagePayment(@ToolParam(description = "该订单的订单标题",required = true) String orderTitle,@ToolParam(description = "创建订单参数-商户订单号,商户订单号字符长度不超过32位",required = true) String outTradeNo
			,@ToolParam(description = "该订单的支付金额，以分为单位",required = true) long totalAmount) {
		String urlStr="";
		try {
			log.info("[mcp-下单]入参outTradeNo[{}],orderTitle[{}],totalAmount[{}]",outTradeNo,orderTitle,totalAmount);
			CreateOrderBO reqBO = new CreateOrderBO();
			reqBO.setOrderTitle(orderTitle);
			reqBO.setOutTradeNo(outTradeNo);
			reqBO.setTotalAmount(totalAmount);
			String biz_content = JSON.toJSONString(reqBO,SerializerFeature.WRITE_MAP_NULL_FEATURES,SerializerFeature.MapSortField);
			String biz_contentEnc = URLEncoder.encode(biz_content, "utf-8");
			String biz_contentEnc2 = URLEncoder.encode(biz_contentEnc, "utf-8");
			log.debug("[mcp-下单]biz_content[{}]",biz_content);
			log.debug("[mcp-下单]biz_contentEnc2[{}]",biz_contentEnc);
			log.debug("[mcp-下单]biz_contentEnc2[{}]",biz_contentEnc2);
			String sign = sign(merchantNo+biz_content);
			String signEnc = URLEncoder.encode(sign, "utf-8");
			log.debug("[mcp-下单]sign[{}]",sign);
			log.debug("[mcp-下单]signEnc[{}]",signEnc);
			StringBuffer sb = new StringBuffer() ;
			StringBuffer urlSb = new StringBuffer() ;
			urlSb.append(systemProperty.getZlzfCashPlatform())
				.append("/payment/createOrderWeb?app_id=")
				.append(merchantNo)
				.append("&biz_content=")
				.append(biz_contentEnc2)
				.append("&sign=")
				.append(signEnc);
			urlStr = urlSb.toString();
			log.debug("[mcp-下单]url原地址[{}]",urlStr);
			sb.append("网页支付链接: [点击完成支付](").append(urlStr).append(")");
			urlStr = sb.toString();
			log.info("[mcp-下单]url Markdown地址[{}]",urlStr);
		} catch (Exception e) {
			log.error("[mcp-下单]异常",e);
		}
		return urlStr;
	}

//	@Override
//	@Tool(name = "createMobilePayment", description = "创建一笔付款订单，返回带有支付链接的 Markdown 文本，该链接在手机浏览器中打开后可跳转到证联APP或直接在浏览器中支付。本工具适用于移动网站或移动 App。")
//	public String createMobilePayment(@ToolParam(description = "该订单的订单标题",required = true) String orderTitle,@ToolParam(description = "创建订单参数-商户订单号,商户订单号字符长度不超过32位",required = true) String outTradeNo
//			,@ToolParam(description = "该订单的支付金额，以分为单位",required = true) long totalAmount){
//		String urlStr="";
//		try {
//			log.info("[mcp-下单Moblile]入参outTradeNo[{}],orderTitle[{}],totalAmount[{}]",outTradeNo,orderTitle,totalAmount);
//			CreateOrderBO reqBO = new CreateOrderBO();
//			reqBO.setOrderTitle(orderTitle);
//			reqBO.setOutTradeNo(outTradeNo);
//			reqBO.setTotalAmount(totalAmount);
//			String biz_content = JSON.toJSONString(reqBO,SerializerFeature.WRITE_MAP_NULL_FEATURES,SerializerFeature.MapSortField);
//			String biz_contentEnc = URLEncoder.encode(biz_content, "utf-8");
//			String biz_contentEnc2 = URLEncoder.encode(biz_contentEnc, "utf-8");
//			log.debug("[mcp-下单Moblile]biz_content[{}]",biz_content);
//			log.debug("[mcp-下单Moblile]biz_contentEnc2[{}]",biz_contentEnc);
//			log.debug("[mcp-下单Moblile]biz_contentEnc2[{}]",biz_contentEnc2);
//			String sign = sign(merchantNo+biz_content);
//			String signEnc = URLEncoder.encode(sign, "utf-8");
//			log.debug("[mcp-下单Moblile]sign[{}]",sign);
//			log.debug("[mcp-下单Moblile]signEnc[{}]",signEnc);
//			StringBuffer sb = new StringBuffer() ;
//			StringBuffer urlSb = new StringBuffer() ;
//			urlSb.append(systemProperty.getZlzfCashPlatform())
//				.append("/payment/createOrderMobile?app_id=")
//				.append(merchantNo)
//				.append("&biz_content=")
//				.append(biz_contentEnc2)
//				.append("&sign=")
//				.append(signEnc);
//			urlStr = urlSb.toString();
//			log.debug("[mcp-下单Moblile]url原地址[{}]",urlStr);
//			sb.append("网页支付链接: [点击完成支付](").append(urlStr).append(")");
//			urlStr = sb.toString();
//			log.info("[mcp-下单Moblile]url Markdown地址[{}]",urlStr);
//		} catch (Exception e) {
//			log.error("[mcp-下单Moblile]异常",e);
//		}
//		return urlStr;
//	}
	@Override
	@Tool(name = "createMobilePayment", description = "创建一笔付款订单，返回带有支付链接的 Markdown 文本，该链接在手机浏览器中打开后可跳转到证联APP或直接在浏览器中支付。本工具适用于移动网站或移动 App。")
	public String createMobilePayment(@ToolParam(description = "该订单的订单标题",required = true) String orderTitle,@ToolParam(description = "创建订单参数-商户订单号,商户订单号字符长度不超过32位",required = true) String outTradeNo
			,@ToolParam(description = "该订单的支付金额，以分为单位",required = true) long totalAmount){
		String urlStr="";
		try {
			log.info("[mcp-下单Moblile]入参outTradeNo[{}],orderTitle[{}],totalAmount[{}]",outTradeNo,orderTitle,totalAmount);
			MessageHeader header = assemMessageHeader(com.zlpay.enums.payacc.BusinessCodeEnum.T0008.getValue(),true);
			T0008ReqBO busiReqBO = assemT0008ReqBO(orderTitle,outTradeNo,totalAmount);
			String reqJsonStr = JSON.toJSONString(busiReqBO,SerializerFeature.WRITE_MAP_NULL_FEATURES,SerializerFeature.SortField);
			String resultStr = post(systemProperty.getZlzfGateway(),header,reqJsonStr);
			MessageResponse response = JSON.parseObject(resultStr, MessageResponse.class);
			if(!MallRespCodeEnum.SUCCESS.getValue().equals(response.getSysRtnCode())) {
				log.error("[mcp-下单Moblile]应答信息response[{}]",response);
				throw new RuntimeException("mcp-mobile下单失败");
			}
			BaseResBO busiRespBO = JSON.parseObject(response.getBizData(), BaseResBO.class);
			if(!PayAccRespCodeEnum.SUCESS.getValue().equals(busiRespBO.getResCode())) {
				log.error("[mcp-下单Moblile]应答信息busiRespBO[{}]",busiRespBO);
				throw new RuntimeException("mcp-mobile下单失败");
			}
			T0008ResBO t0008ResBO = JSON.parseObject(busiRespBO.getResData(), T0008ResBO.class);
			
			StringBuffer sb = new StringBuffer() ;
			StringBuffer urlSb = new StringBuffer() ;
			urlSb.append("demosdk://index?token=")
				.append(t0008ResBO.getToken());
			urlStr = urlSb.toString();
			log.debug("[mcp-下单Moblile]url原地址[{}]",urlStr);
			sb.append("网页支付链接: [点击完成支付](").append(urlStr).append(")");
			urlStr = sb.toString();
			log.info("[mcp-下单Moblile]url Markdown地址[{}]",urlStr);
		} catch (Exception e) {
			log.error("[mcp-下单Moblile]异常",e);
		}
		return urlStr;
	}
	
	private T0008ReqBO assemT0008ReqBO(String orderTitle,String outTradeNo,long totalAmount) {
		T0008ReqBO reqBO = new T0008ReqBO();
		//877商户appid
		reqBO.setAppId("F41975C498B44D6EE053376010ACB761");
		/**
		 * 注：付款方的信息确认
		 * 1、谁打开谁支付。依托于后续事实操作 
		 * 2、依托于智能体应用环境的配置账号。基于证联产品现状，暂选实现方式。
		 * */
		//b306a1ce4dddefb7ffb448b1dc2e37c6 测试sdk 130582198910012041 18810001001 CP0000058357 qq112233
		reqBO.setAppUserId("b306a1ce4dddefb7ffb448b1dc2e37c6");
		reqBO.setUserName("测试sdk");
		reqBO.setCertNo("130582198910012041");
		reqBO.setPhone("18810001001");
		PayeeInfo payeeInfo = new PayeeInfo();
		payeeInfo.setMerchantSeqId(outTradeNo);
		payeeInfo.setAmount(totalAmount);
		payeeInfo.setSubType(SubAccessTypeEnum.PAY.getValue());;
		reqBO.setPayeeInfo(payeeInfo);
		reqBO.setType(AccessTypeEnum.PAY.getValue());
		
		
		TradeTerminalBO terB0  = new TradeTerminalBO();
		terB0.setIp("127.0.0.1");
		terB0.setTerminal(TerminalEnum.OTHER.getValue());
		terB0.setMac("00-0E-C6-78-A4-D2");
		reqBO.setTradeTerminalInfo(terB0);
		return reqBO ;
	}
	
	@Override
	@Tool(name = "queryPayment", description = "查询一笔付款订单，并返回带有订单信息的文本。 ")
	public String queryPayment(@ToolParam(description = "商户订单号,商户订单号字符长度不超过32位",required = true) String outTradeNo) {
		String urlStr="";
		try {
			log.info("[mcp-订单查询]，入参outTradeNo[{}]",outTradeNo);
			MessageHeader header = assemMessageHeader(BusinessCodeEnum.T00006,true);
			QueryOrderStateReqBO busiReqBO = assemQueryOrderStateReqBO(outTradeNo);
			String reqJsonStr = JSON.toJSONString(busiReqBO,SerializerFeature.WRITE_MAP_NULL_FEATURES,SerializerFeature.SortField);
			String resultStr = post(systemProperty.getZlzfGateway(),header,reqJsonStr);
			MessageResponse response = JSON.parseObject(resultStr, MessageResponse.class);
			if(!MallRespCodeEnum.SUCCESS.getValue().equals(response.getSysRtnCode())) {
				log.error("[mcp-订单查询]应答信息response[{}]",response);
				throw new RuntimeException("订单状态查询失败");
			}
			QueryOrderStateRespBO busiRespBO = JSON.parseObject(response.getBizData(), QueryOrderStateRespBO.class);
			if(!MallRespCodeEnum.SUCCESS.getValue().equals(busiRespBO.getRespCode())) {
				log.error("[mcp-订单查询]应答信息busiRespBO[{}]",busiRespBO);
				throw new RuntimeException("订单状态查询失败");
			}
			String orderStatusDesc = MallRespCodeEnum.FAIL.getDisplayName();
			if(MallRespCodeEnum.SUCCESS.getValue().equals(busiRespBO.getStatus())) {
				orderStatusDesc =  MallRespCodeEnum.SUCCESS.getDisplayName();
			}else if(MallRespCodeEnum.UNKNOWN.getValue().equals(busiRespBO.getStatus())) {
				orderStatusDesc = MallRespCodeEnum.UNKNOWN.getDisplayName();
			}
			StringBuffer sb = new StringBuffer();
			sb.append("# 订单信息如下")
			.append("**订单号：**").append(busiRespBO.getOrderId())
			.append("**订单状态：**").append(orderStatusDesc)
			.append("**结算日期：**").append(busiRespBO.getSettleDate())
			//.append("**银行流水号：**").append(busiRespBO.getBankSn())
			.append("**创建时间：**").append(busiRespBO.getCreateTimeStr());
			
			urlStr = sb.toString();
			log.info("[mcp-订单查询]出参[{}]",urlStr);
		} catch (Exception e) {
			log.error("[mcp-订单查询]异常",e);
		}
		return urlStr;
	}
	
	private QueryOrderStateReqBO assemQueryOrderStateReqBO( String outTradeNo) {
		
		QueryOrderStateReqBO reqBO = new QueryOrderStateReqBO();
		reqBO.setSeqId(generateUuid());
		reqBO.setOrgSeqId(outTradeNo);
		reqBO.setTradeType(QueryTradeType.TYPE_1.getValue());
		
		TradeTerminalBO terB0  = new TradeTerminalBO();
		terB0.setIp("127.0.0.1");
		terB0.setTerminal(TerminalEnum.OTHER.getValue());
		terB0.setMac("00-0E-C6-78-A4-D2");
		reqBO.setTradeTerminalInfo(terB0);
		return reqBO ;
	}
	
	@Override
	@Tool(name = "refundPayment", description = "对交易发起退款，并返回退款状态和退款金额。")
	public String refundPayment(@ToolParam(description = "退款请求号,退款请求号字符长度不超过32位",required = true) String outRequestNo,@ToolParam(description = "商户订单号,商户订单号字符长度不超过32位",required = true) String outTradeNo
			,@ToolParam(description = "退款金额，以分为单位",required = true) long refundAmount, @ToolParam(description = "退款原因")String refundReason) {
		String urlStr="";
		try {
			log.info("[mcp-退款]，入参outTradeNo[{}],outRequestNo[{}],refundAmount[{}],refundReason[{}]",outTradeNo,outRequestNo,refundAmount,refundReason);
			
			MessageHeader header = assemMessageHeader(BusinessCodeEnum.T00005,true);
			RefundReqBO busiReqBO = assemRefundReqBO(outRequestNo,outTradeNo,refundAmount);
			String reqJsonStr = JSON.toJSONString(busiReqBO,SerializerFeature.WRITE_MAP_NULL_FEATURES,SerializerFeature.SortField);
			String resultStr = post(systemProperty.getZlzfGateway(),header,reqJsonStr);
			MessageResponse response = JSON.parseObject(resultStr, MessageResponse.class);
			if(!MallRespCodeEnum.SUCCESS.getValue().equals(response.getSysRtnCode())) {
				log.error("[mcp-订单查询]应答信息response[{}]",response);
				throw new RuntimeException("订单状态查询失败");
			}
			RefundRespBO busiRespBO = JSON.parseObject(response.getBizData(), RefundRespBO.class);
			if(!MallRespCodeEnum.SUCCESS.getValue().equals(busiRespBO.getRespCode())) {
				log.error("[mcp-订单查询]应答信息busiRespBO[{}]",busiRespBO);
				throw new RuntimeException("订单状态查询失败");
			}
			
			String orderStatusDesc = MallRespCodeEnum.FAIL.getDisplayName();
			if(MallRespCodeEnum.SUCCESS.getValue().equals(busiRespBO.getRespCode())) {
				orderStatusDesc =  MallRespCodeEnum.SUCCESS.getDisplayName();
			}else if(MallRespCodeEnum.UNKNOWN.getValue().equals(busiRespBO.getRespCode())) {
				orderStatusDesc = MallRespCodeEnum.UNKNOWN.getDisplayName();
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("# 订单信息如下")
			.append("**订单状态：**").append(orderStatusDesc)
			.append("**结算日期：**").append(busiRespBO.getSettleDate());
			//.append("**银行流水号：**").append(busiRespBO.getBankSn());
			
			urlStr = sb.toString();
			log.info("[mcp-订单查询]出参[{}]",urlStr);
			
		} catch (Exception e) {
			log.error("[mcp-退款]异常",e);
		}
		return urlStr;
	}
	
	private RefundReqBO assemRefundReqBO(String outRequestNo,String outTradeNo,long refundAmount) {
		RefundReqBO reqBO = new RefundReqBO();
		reqBO.setSeqId(outRequestNo);
		reqBO.setOrgSeqId(outTradeNo);
		reqBO.setAmount(refundAmount+"");
		
		TradeTerminalBO terB0  = new TradeTerminalBO();
		terB0.setIp("127.0.0.1");
		terB0.setTerminal(TerminalEnum.OTHER.getValue());
		terB0.setMac("00-0E-C6-78-A4-D2");
		reqBO.setTradeTerminalInfo(terB0);
		return reqBO ;
	}
	
	@Override
	@Tool(name = "queryRefund", description = "查询一笔退款订单，并返回退款状态和退款金额。")
	public String queryRefund(@ToolParam(description = "退款请求号,退款请求号字符长度不超过32位",required = true) String outRequestNo,@ToolParam(description = "商户订单号,商户订单号字符长度不超过32位",required = true) String outTradeNo) {
		String urlStr="";
		try {
			log.info("[mcp-退款查询]，入参outTradeNo[{}],outRequestNo[{}]",outTradeNo,outRequestNo);
			
			MessageHeader header = assemMessageHeader(BusinessCodeEnum.T00006,true);
			QueryOrderStateReqBO busiReqBO = assemQueryOrderStateReqBO(outRequestNo);
			String reqJsonStr = JSON.toJSONString(busiReqBO,SerializerFeature.WRITE_MAP_NULL_FEATURES,SerializerFeature.SortField);
			String resultStr = post(systemProperty.getZlzfGateway(),header,reqJsonStr);
			MessageResponse response = JSON.parseObject(resultStr, MessageResponse.class);
			if(!MallRespCodeEnum.SUCCESS.getValue().equals(response.getSysRtnCode())) {
				log.error("[mcp-订单查询]应答信息response[{}]",response);
				throw new RuntimeException("订单状态查询失败");
			}
			QueryOrderStateRespBO busiRespBO = JSON.parseObject(response.getBizData(), QueryOrderStateRespBO.class);
			if(!MallRespCodeEnum.SUCCESS.getValue().equals(busiRespBO.getRespCode())) {
				log.error("[mcp-订单查询]应答信息busiRespBO[{}]",busiRespBO);
				throw new RuntimeException("订单状态查询失败");
			}
			String orderStatusDesc = MallRespCodeEnum.FAIL.getDisplayName();
			if(MallRespCodeEnum.SUCCESS.getValue().equals(busiRespBO.getStatus())) {
				orderStatusDesc =  MallRespCodeEnum.SUCCESS.getDisplayName();
			}else if(MallRespCodeEnum.UNKNOWN.getValue().equals(busiRespBO.getStatus())) {
				orderStatusDesc = MallRespCodeEnum.UNKNOWN.getDisplayName();
			}
			StringBuffer sb = new StringBuffer();
			sb.append("# 订单信息如下")
			.append("**订单号：**").append(busiRespBO.getOrderId())
			.append("**订单状态：**").append(orderStatusDesc)
			.append("**结算日期：**").append(busiRespBO.getSettleDate())
			//.append("**银行流水号：**").append(busiRespBO.getBankSn())
			.append("**创建时间：**").append(busiRespBO.getCreateTimeStr());
			
			urlStr = sb.toString();
			log.info("[mcp-订单查询]出参[{}]",urlStr);
			
		} catch (Exception e) {
			log.error("[mcp-退款查询]异常",e);
		}
		return urlStr;
	}
	
	private String sign(String data) {
		try {
			return com.zlpay.assist.sign.sm2.SM2Util.sign(privateKey, serialNo, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		throw new RuntimeException("签名失败");
	}
	
	private void verify(MessageBody respBody) {
		boolean success = false;
		try {
			success = com.zlpay.assist.sign.sm2.SM2Util.verify(publicKey, zlSerialNo, respBody.getSign(), respBody.getData());
		} catch (Exception e) {
			log.error("[验签报文]程序异常",e);
		}
		if(!success) {
			throw new RuntimeException("验签失败");
		}
	}
	
	private String decryptData(String key,String src) {
		try {
			byte[] desrcHex = Base64.decode(src);
			byte[] deresult = SM4Util.sm4EcbDecrypt(key.getBytes(), desrcHex, Constant.PAD_NO);
			return new String(deresult, Constant.UTF8);
		} catch (Exception e) {
			log.error("[解密报文]程序异常!!", e);
		}
		throw new RuntimeException("解密报文失败");
	}
	
	private String post(String url, MessageHeader messageHeader, String jsonData) throws Exception{
		try {
			MessageBody body = new MessageBody();
			if("1".equals(messageHeader.getEncrp())) {
				// 生成对称加密秘钥
				String key = generateKey(16);
				// 加密数据
				jsonData = com.zlpay.assist.encrypt.sm4.SM4Util.sm4EcbEncrypt(key, jsonData, "NoPadding");
				//加密对称加密的秘钥
				String secrtKey = com.zlpay.assist.sign.sm2.SM2Util.encrypt(publicKey, key);
				// 将密文放入body
				body.setSecret(secrtKey);
			}
			body.setData(jsonData);
			
			body.setSign(sign(jsonData));
			
			MessageBody messageBody =HttpClientUtil.doPost(systemProperty.getZlzfGateway(), messageHeader, JSON.toJSONString(body));
			
			//验签
			verify(messageBody);
			String resultData = messageBody.getData();
			
			if("1".equals(messageHeader.getEncrp())) {
				String key = com.zlpay.assist.sign.sm2.SM2Util.decrypt(privateKey,messageBody.getSecret());
				resultData = decryptData(key, messageBody.getData());
			}
			return resultData;
		} catch (Exception e) {
			log.error("[mcp-发送请求]程序异常!!",e);
		}
		return null ;
	}
	private String generateKey(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	private String generateUuid() {
		return UUID.randomUUID().toString().replace("-", "").toString();
	}
	
	private MessageHeader assemMessageHeader(BusinessCodeEnum txCode,boolean encrp) {
		return assemMessageHeader(txCode.getValue(),encrp) ;
	}
	private MessageHeader assemMessageHeader(String txCode,boolean encrp) {
		MessageHeader header = new MessageHeader();
		header.setMsgId(generateUuid() );
		header.setMerchNo(merchantNo);
		header.setTxCode(txCode);
		header.setVersion(Constant.VERSION_NO);
		header.setSignNo(serialNo);
		if(encrp) {
			header.setEncrp("1");
		}else {
			header.setEncrp("2");
			
		}
		header.setEncrpNo(zlSerialNo);
		header.setTimestamp(df.format(new Date()));
		header.setSigna("1");
		return header ;
	}
}
