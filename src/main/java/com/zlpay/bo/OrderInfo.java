package com.zlpay.bo;

import lombok.Data;

@Data
public class OrderInfo {
	
	private String orderId;
	private long amount ;
	private UserInfo payerInfo;
	private UserInfo payeeInfo;
	/**
	 * 0 已生成
	 * 1 已付款
	 * 2 已收款
	 */
	private String status ;
}
