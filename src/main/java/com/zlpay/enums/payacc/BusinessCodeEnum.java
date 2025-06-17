package com.zlpay.enums.payacc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:支付账户SDK
 * 业务编码枚举，总共六位，分为三段。
 * 第一段两位数字代表具体系统(跨境前置默认先占01)；
 * 第二段为两位数字，代表业务范围；
 * 第三段为二位数据，代表具体的业务
 * @Auther: maln
 * @Date: 2022/8/18 16:43
 */
public enum BusinessCodeEnum {

    /**
     * 获取token
     */
    T0001("ZLPAY.ACC.T0001", "获取token"),
    /**
     * 查询交易状态
     */
    T0002("ZLPAY.ACC.T0002", "查询交易状态"),
    /**
     * 退款
     */
    T0003("ZLPAY.ACC.T0003", "退款"),
    /**
     * 支付回调通知
     */
    T0004("ZLPAY.ACC.T0004", "支付回调通知"),
    /**
     * 证联客户号推送
     */
    T0005("ZLPAY.ACC.T0005", "证联客户号推送"),
    /**
     * 客户签约状态推送
     */
    T0006("ZLPAY.ACC.T0006", "客户签约状态推送"),
    /**
     * 余额查询
     */
    T0007("ZLPAY.ACC.T0007", "余额查询"),
    /**
     * 预下单
     */
    T0008("ZLPAY.ACC.T0008", "预下单"),
    /**
     *查询客户信息是否完善
     */
    T00011("ZLPAY.ACC.T0011", "查询客户信息是否完善"),
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
    public static BusinessCodeEnum parseOf(String value ) {
        for ( BusinessCodeEnum item : values() )
            if ( item.getValue().equals( value ) )
                return item;

        throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
    }

}
