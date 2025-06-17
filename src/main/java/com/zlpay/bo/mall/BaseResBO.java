package com.zlpay.bo.mall;

import com.zlpay.enums.mall.MallRespCodeEnum;

import lombok.Data;

/** 
 * @Description: 应答结果基类对象
 * @author: tsl
 * @date: 2020年8月14日 下午2:05:31  
 */
@Data
public class BaseResBO {
	
	/**
	 * 应答码
	 */
	private String respCode;
	/**
	 * 应答描述
	 */
	private String respMsg;
	
	public BaseResBO() {
	}
	
	public BaseResBO(String respCode,String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}
	
	public BaseResBO(MallRespCodeEnum resp) {
		this.respCode = resp.getValue();
		this.respMsg = resp.getDisplayName();
	}
	
}
