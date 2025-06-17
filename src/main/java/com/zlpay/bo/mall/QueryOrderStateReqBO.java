package com.zlpay.bo.mall;

import javax.validation.constraints.Size;

import com.zlpay.enums.mall.QueryTradeType;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class QueryOrderStateReqBO extends BaseReqBO{
	/**
	 * 流水号
	 */
	@NotBlank(message="流水号不能为空")
	@Size(max=32,message="流水号长度最大32位")
	private String seqId;
	/**
	 * 原交易流水
	 */
	@NotBlank(message="原交易流水不能为空")
	@Size(max=32,message="原交易流水长度最大32位")
	private String orgSeqId;
	
	//2023-7-27
	@NotBlank(message="交易类型不能为空")
	@EnumValidator(value= QueryTradeType.class,message="交易类型错误")
	private String tradeType;
}
