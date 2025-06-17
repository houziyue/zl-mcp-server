package com.zlpay.bo.payacc;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.zlpay.constant.Constant;
import com.zlpay.enums.payacc.PayModeEnum;
import com.zlpay.enums.payacc.SubAccessTypeEnum;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description: 收款信息域
 * @author: HongBin.Shan
 * @date: 2023年08月14日  下午 2:37
 */
@Data
public class PayeeInfo {

    /**
     * 商户请求流水号
     */
    @NotBlank(message = "merchantSeqId" + Constant.ERROR_PARAM_NOTNULL)
    @Size(max = 32, message = "merchantSeqId" + Constant.ERROR_PARAM_LENGTH)
    private String merchantSeqId;


    /**
     * 金额（单位：分）
     */
    //@NotNull(message = "amount" + Constant.ERROR_PARAM_NOTNULL)
    @Range(min = 0L, max = 999999999999999L, message = "amount取值超过规定范围")
    private Long amount;

    /**
     * 访问子类型
     */
    @NotBlank(message = "subType" + Constant.ERROR_PARAM_NOTNULL)
    @EnumValidator(value = SubAccessTypeEnum.class, message = "subType" + Constant.ERROR_PARAM_ENUM)
    private String subType;

    /**
     * 收款客户号（转账交易必填）
     */
    @Size(max = 12, message = "payeeCustId" + Constant.ERROR_PARAM_LENGTH)
    private String payeeCustId;

    /**
     * 收款方姓名
     */
    @Size(max = 200, message = "payeeName" + Constant.ERROR_PARAM_LENGTH)
    private String payeeName;

    /**
     * 备注
     */
    @Size(max = 250, message = "remark" + Constant.ERROR_PARAM_LENGTH)
    private String remark;

    /**
     * 是否支持代付
     */
    private String pfaFlag;
    /**
	 *分账标识
	 *2023-9-27
	 */
	//@NotBlank(message="分账标识不可空")
	//@EnumValidator(value= ProfitSharingEnum.class,message="分账标识错误")
    private String profitSharing ;
    /**
    * 支付方式
    */
    @EnumValidator(value = PayModeEnum.class, message = "payMode" + Constant.ERROR_PARAM_ENUM)
    private String payMode;

    /**
    * 商户支持微信支付或者云闪付时需上送。为JSON格式数组
    */
    private List<PayParam> payParam;

    /**
    * 商户收银台支付时，选择的支付方式（收银台选择验密支付时必填）
    */
    @Size(max = 50, message = "payToken" + Constant.ERROR_PARAM_LENGTH)
    private String payToken;
}
