package com.zlpay.enums.payacc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 访问子类型枚举
 * @author: HongBin.Shan
 * @date: 2023年08月14日  下午 2:16
 */
@Getter
@AllArgsConstructor
public enum SubAccessTypeEnum {
    PAY("0100", "支付", AccessTypeEnum.PAY),
    TRANSFER("0101", "转账", AccessTypeEnum.PAY),
    RED_ENVELOPE("0102", "红包", AccessTypeEnum.PAY),
    SCAN_CODE_TRANSFER("0103", "扫码转账", AccessTypeEnum.PAY),
    PASSWORD_PAY("0701", "验密支付", AccessTypeEnum.PAY),
    WECHAT_PAY("0702", "微信支付", AccessTypeEnum.PAY),
    CLOUD_FLASH_PAY("0703", "云闪付支付", AccessTypeEnum.PAY),
    ENTRAUSTED_PAY("0201", "委托代扣款并签约", AccessTypeEnum.ENTRAUSTED_PAY),
    ;

    private String value;

    private String displayName;

    private AccessTypeEnum accessType;

    public static SubAccessTypeEnum parseOf(String value) {
        for (SubAccessTypeEnum item : SubAccessTypeEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("枚举值[" + value + "]不匹配");
    }
}
