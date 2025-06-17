package com.zlpay.enums.payacc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 委托代扣扣款周期枚举
 * @author: HongBin.Shan
 * @date: 2025年01月06日  上午 11:13
 */
@AllArgsConstructor
@Getter
public enum DeductionPeriodEnum {

    MONTHLY("month", "按月"),
    QUARTERLY("quarter", "按季"),
    ANNUALLY("year", "按年"),
    ;

    private String value;

    private String displayName;

    public static DeductionPeriodEnum parseOf(String value) {
        for (DeductionPeriodEnum item : DeductionPeriodEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("枚举值[" + value + "]不匹配;");
    }
}
