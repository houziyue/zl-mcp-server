/**   
 * Copyright © 2018 zlpay.
 */
package com.zlpay.bo.gateway;

import lombok.Data;

/** 
 * @Description: 应答数据
 * @author: syuf
 * @date: 2018年10月17日 下午5:58:35  
 */
@Data
public class MessageResponse {

	/**
	 * 系统请求应答码
	 */
	private String sysRtnCode;
	/**
	 * 系统请求应答描述
	 */
	private String sysRtnMsg;
	/**
	 * 业务应答报文串
	 */
	private String bizData;
}
