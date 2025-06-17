package com.zlpay.enums.payacc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 客户扣款协议类型
 * @author: HongBin.Shan
 * @date: 2025年01月06日  上午 11:07
 */
@AllArgsConstructor
@Getter
public enum AgreementTypeEnum {
	
	NONE("0", "不启用协议"),
    SECRET_FREE_PAYMENT("1", "免密支付"),
    ENTRUSTED_WITHHOLDING("2", "委托代扣"),
    ;

    private String value;

    private String displayName;

    public static AgreementTypeEnum parseOf(String value) {
        for (AgreementTypeEnum item : AgreementTypeEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("枚举值[" + value + "]不匹配！");
    }

}
