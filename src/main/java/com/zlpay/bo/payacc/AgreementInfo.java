package com.zlpay.bo.payacc;

import com.zlpay.enums.payacc.AgreementTypeEnum;
import com.zlpay.enums.payacc.DeductionPeriodEnum;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description: 委托代扣协议信息域
 * @author: HongBin.Shan
 * @date: 2025年01月06日  上午 11:40
 */
@Data
public class AgreementInfo {

    /**
     * 协议类型
     */
    @NotBlank(message = "agreementType不能为空" )
    @EnumValidator(value = AgreementTypeEnum.class, message = "agreementType枚举值不正确")
    private String agreementType;

    private String serviceName;

    private String serviceDesc;

    @NotBlank(message =  "limit不能为空")
    private String limit;

    @EnumValidator(value = DeductionPeriodEnum.class, message = "paymentPeriod枚举值不正确")
    private String paymentPeriod;

    private String paymentDate;

    private String recordNo;
}
