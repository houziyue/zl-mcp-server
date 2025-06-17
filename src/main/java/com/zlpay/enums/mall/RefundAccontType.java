package com.zlpay.enums.mall;

import java.util.HashMap;
import java.util.Map;

public enum RefundAccontType {
	UNSETTLED("UNSETTLED", "未结算资金"),
	AVAILABLE("AVAILABLE", "自有资金可用余额"),
	;

	private String value;
	private final String displayName;

	private static Map<String, RefundAccontType> valueMap = new HashMap<String, RefundAccontType>();

	static {
		for ( RefundAccontType _enum : RefundAccontType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	RefundAccontType( String value, String displayName ) {
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
	public static RefundAccontType parseOf( String value ) {
		for ( RefundAccontType item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "类型[" + value + "]不匹配!" );
	}
}
