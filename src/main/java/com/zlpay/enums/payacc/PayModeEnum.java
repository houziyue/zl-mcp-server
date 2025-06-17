package com.zlpay.enums.payacc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:实名；匿名支付方式
 * @Auther: maln
 * @Date: 2023/11/13 10:13
 */
@Getter
@AllArgsConstructor
public enum PayModeEnum {

    REAL_NAME("0", "匿名支付"),
    ANONYMOUS("1", "实名支付"),
    ;

    private String value;

    private String displayName;

    public static PayModeEnum parseOf(String value) {
        for (PayModeEnum item : PayModeEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("枚举值[" + value + "]不匹配");
    }
}
