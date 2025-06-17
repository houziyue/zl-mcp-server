package com.zlpay.enums.mall;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86188
 * 订单状态查询类型枚举
 * 2023-7-27
 */
public enum QueryTradeType {
	TYPE_1("1","支付"),
	TYPE_2("2","预下单"),
	TYPE_3("3","合单支付"),
	TYPE_4("4","合单预下单"),
	TYPE_5("5","退款"),
	TYPE_6("6","转账"),
	TYPE_7("7","充值"),
	TYPE_8("8","订单入金"),
	TYPE_9("9","订单入金退款"),
	TYPE_10("10","资金代付"),
	;
	
	private String value;
	private final String displayName;
	

	private static Map<String, QueryTradeType> valueMap = new HashMap<String, QueryTradeType>();

	static {
		for ( QueryTradeType _enum : QueryTradeType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	QueryTradeType( String value, String displayName ) {
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
	public static QueryTradeType parseOf( String value ) {
		for ( QueryTradeType item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "银行返回码[" + value + "]不匹配!" );
	}	
}
