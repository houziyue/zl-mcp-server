package com.zlpay.bo.mall;

import javax.validation.constraints.Size;

import com.zlpay.enums.mall.MallRespCodeEnum;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class QueryOrderStateRespBO extends BaseResBO{
	/**
	 * 业务状态
	 */
	@NotBlank(message="status不能为空")
	@Size(max=6,min=6,message="status长度最大6位")
	@EnumValidator(value=MallRespCodeEnum.class,message="status枚举值非法")
	private String status;
	/**
	 * 状态描述
	 */
	@NotBlank(message="msg不能为空")
	@Size(max=120,message="msg长度最大120位")
	private String msg;
	//结算日期 yyyymmdd 
	private String settleDate ;
	//创建时间  yyyymmddhhmmss 
	private String createTimeStr ;

	private String orderId;

	private String subOptType;

	private String bankCardToken;

	private String tradeTime;

	private String amount;

	private String agreementNo;
	//银行流水号 交易成功时有值
	private String bankSn;
}
