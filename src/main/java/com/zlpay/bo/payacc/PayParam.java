package com.zlpay.bo.payacc;

import javax.validation.constraints.Size;

import com.zlpay.enums.BankTypeEnum;
import com.zlpay.enums.payacc.LimitPayEnum;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description:支付参数BO
 * @Auther: maln
 * @Date: 2023/11/10 17:02
 */
@Data
public class PayParam {

    /**
     * 商户备案分配的子商户号
     */
    @Size(max = 32 ,message = "subMchId长度最大32位")
    private String subMchId;
    /**
     * 子商户微信appId
     */
    @Size(max = 32 ,message = "subAppId长度最大32位")
    private String subAppId;
    /**
     * 付款人的openId
     */
    @Size(max = 128 ,message = "subOpenId长度最大128位")
    private String subOpenId;
    /**
     * 备案时的商户名称
     */
    @Size(max = 50 ,message = "merchantName长度最大50位")
    private String merchantName;
    /**
     * 终端设备。PC 网页或公众号内支付请传"WEB"。
     * 银联微信支付不可空
     */
    @Size(max = 12 ,message = "deviceInfo长度最大12位")
    private String deviceInfo;
    /**
    * 传入公众号名称
     * -实际商品名称，例如：腾讯形象店- image-QQ 公仔；
    */
    @NotBlank(message = "description不能为空")
    @Size(max = 32 ,message = "description长度最大32位")
    private String description;
    /**
    * 有效时间。单位“秒”，默认300秒。
    */
    private Short expireTime;
    /**
    * 交易类型
    */
    @Size(max = 4 ,message = "tradeType长度最大4位")
    private String tradeType;
    /**
    * 禁用信用卡标识
    */
    @EnumValidator(value = LimitPayEnum.class, message = "limitPay" + "枚举值不存在")
    private String limitPay;
    /**
     * 商户所在地地区信息， 6 位定长，精确到区县编码维度
     */
    @NotBlank(message = "areaInfo不能为空")
    @Size(max = 6 ,message = "areaInfo长度最大6位")
    private String areaInfo;
    /**
    * 支付成功后推送通知
    */
    @NotBlank(message = "backUrl不能为空")
    @Size(max = 256 ,message = "backUrl长度最大256位")
    private String backUrl;
    /**
    * 0905-银联云闪付
     * 0906-银联微信
    */
    @EnumValidator(value = BankTypeEnum.class, message = "channelType" + "枚举值不存在")
    private String channelType;
}
