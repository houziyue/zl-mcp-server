package com.zlpay.bo.mall;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * @Description: 退款应答BO
 * @author: hzy
 * @date: 2025年4月23日 下午2:36:21  
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class RefundRespBO extends BaseResBO{
	/**
	 * 结算日期
	 */
	private String settleDate ;
	/**
	 * 交易成功时有值。微信预下单支付，返回的为微信的订单号
	 */
	private String bankSn ;
	
	
}
