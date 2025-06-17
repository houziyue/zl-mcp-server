package com.zlpay.bo.payacc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zlpay.bo.mall.BaseReqBO;
import com.zlpay.bo.mall.TradeTerminalBO;
import com.zlpay.constant.Constant;
import com.zlpay.enums.payacc.AccessTypeEnum;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 预下单请求报文
 * @author: HongBin.Shan
 * @date: 2023年08月14日  下午 4:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class T0008ReqBO extends BaseReqBO {

    /**
     * appId
     */
    @NotBlank(message = "appId" + Constant.ERROR_PARAM_NOTNULL)
    @Size(max = 32, min = 32, message = "appId" + Constant.ERROR_PARAM_LENGTH)
    private String appId;
    /**
     * app端用户唯一标识
     */
    @NotBlank(message = "appUserId" + Constant.ERROR_PARAM_NOTNULL)
    @Size(max = 32, message = "appUserId" + Constant.ERROR_PARAM_LENGTH)
    private String appUserId;
    /**
     * 姓名
     */
    @NotBlank(message = "userName" + Constant.ERROR_PARAM_NOTNULL)
    @Size(max = 64, message = "userName" + Constant.ERROR_PARAM_LENGTH)
    private String userName;
    /**
     * 证件号码
     */
    @NotBlank(message = "cretNo" + Constant.ERROR_PARAM_NOTNULL)
    @Size(max = 20, message = "cretNo" + Constant.ERROR_PARAM_LENGTH)
    private String certNo;
    /**
     * 手机号
     */
    @NotBlank(message = "phone" + Constant.ERROR_PARAM_NOTNULL)
    @Size(min = 11, max = 11, message = "phone" + Constant.ERROR_PARAM_LENGTH)
    private String phone;

    /**
     * 收款信息域
     */
    @NotNull(message = "payeeInfo" + Constant.ERROR_PARAM_NOTNULL)
    private PayeeInfo payeeInfo;

    /**
     * 访问类型
     */
    @Size(max = 2, min = 2, message = "type" + Constant.ERROR_PARAM_LENGTH)
    @EnumValidator(value = AccessTypeEnum.class, message="type" + Constant.ERROR_PARAM_ENUM)
    private String type;

    private TradeTerminalBO tradeTerminalInfo;

    /**
     * 委托代扣协议信息域
     */
    private AgreementInfo agreementInfo;
}
