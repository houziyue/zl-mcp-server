package com.zlpay.bo;

import lombok.Data;

@Data
public class CreateOrderBO {
	
	private String orderTitle; 
	private String outTradeNo; 
	private Long totalAmount; 
}
