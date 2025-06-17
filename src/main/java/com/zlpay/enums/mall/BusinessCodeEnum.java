package com.zlpay.enums.mall;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description: 业务编码枚举，总共六位，分为三段。
 * 第一段两位数字代表具体系统(跨境前置默认先占01)；
 * 第二段为两位数字，代表业务范围；
 * 第三段为二位数据，代表具体的业务
 * @author: zy.h
 * @date: 2018年11月8日 下午5:46:49
 */
public enum BusinessCodeEnum {
	
	/**
	 * 发送短信	商户->证联
	 */
	AC0001("ZLPAY.MALL.AC0001","发送短信"),
	/**
	 * 开户	商户->证联
	 */
	AC0002("ZLPAY.MALL.AC0002","开户"),
	/**
	 * 添加银行卡	商户->证联
	 */
	AC0003("ZLPAY.MALL.AC0003","添加银行卡"),
	
	/**
	 * 删卡 商户->证联
	 */
	AC0004("ZLPAY.MALL.AC0004","删卡"),
	
	/**
	 * 充值	商户->证联
	 */
	T00001("ZLPAY.MALL.T00001","充值"),
	/**
	 * 转账	商户->证联
	 */
	T00002("ZLPAY.MALL.T00002","转账"),
	/**
	 * 提现	商户->证联
	 */
	T00003("ZLPAY.MALL.T00003","提现"),
	/**
	 * 支付	商户->证联
	 */
	T00004("ZLPAY.MALL.T00004","支付"),
	/**
	 * 退款	商户->证联
	 */
	T00005("ZLPAY.MALL.T00005","退款"),
	/**
	 * 交易状态查询	商户->证联
	 */
	T00006("ZLPAY.MALL.T00006","交易状态查询"),
	/**
	 * 余额查询	商户->证联
	 */
	T00007("ZLPAY.MALL.T00007","余额查询"),
	/**
	 * 提现/转账回执	证联->商户
	 */
	T00008("ZLPAY.MALL.T00008","提现/转账回执"),
	/**
	 * 订单入金备案
	 */
	T00009("ZLPAY.MALL.T00009", "订单入金备案"),
	/**
	 * 订单入金取消
	 */
	T00010("ZLPAY.MALL.T00010", "订单入金取消"),
	/**
	 * 订单入金核查
	 */
	T00011("ZLPAY.MALL.T00011", "订单入金核查"),
	/**
	 * 订单退款
	 */
	T00012("ZLPAY.MALL.T00012", "订单退款"),
	/**
	 * 红包领取
	 */
	T00013("ZLPAY.MALL.T00022", "红包领取"),
	/**
	 * 红包退款
	 */
	T00014("ZLPAY.MALL.T00023", "红包退款"),
	/**
	 * 交易状态查询
	 */
	Q00001("ZLPAY.MALL.Q00001", "交易状态查询"),
	/**
	 * 订单入金回执
	 */
	N00001("ZLPAY.MALL.N00001", "订单入金回执"),
	/**
	 * 租赁订单入金回执
	 */
	N00002("ZLPAY.MALL.N00002", "租赁订单入金回执"),
	/**
	 * 获取信息维护授权链接
	 */
	AC0005("ZLPAY.MALL.AC0005", "获取信息维护授权链接"),
	/**
	 * 客户附加信息补录
	 */
	AC0006("ZLPAY.MALL.AC0006", "客户附加信息补录"),
	/**
	 * 创建自由资金账户
	 */
	AC0007("ZLPAY.MALL.AC0007", "创建自有资金客户"),
	/**
	 * 创建支付账户
	 */
	AC0008("ZLPAY.MALL.AC0008", "创建支付账户"),
	/**
	 * 文件上传
	 */
	F00001("ZLPAY.MALL.F00001", "文件上传"),
	
	/**
	 * 异步结果通知   证联->商户
	 */
	N00004("ZLPAY.MALL.N00004", "异步结果通知"),
	
	/**
	 * 商户信息备案  商户->证联
	 */
	AC0010("ZLPAY.MALL.AC0010", "商户信息备案"),

	;

	private String value;
	private final String displayName;

	private static Map<String, BusinessCodeEnum> valueMap = new HashMap<String, BusinessCodeEnum>();

	static {
		for ( BusinessCodeEnum _enum : BusinessCodeEnum.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	BusinessCodeEnum( String value, String displayName ) {
		this.value = value;
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return this.getDisplayName();
	}
	
	/**
	 * 枚举转换
	 */
	public static BusinessCodeEnum parseOf( String value ) {
		for ( BusinessCodeEnum item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
}
