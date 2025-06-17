package com.zlpay.bo.mall;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zlpay.enums.mall.RefundAccontType;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RefundReqBO extends BaseReqBO{
	/**
	 * 流水号
	 */
	@NotBlank(message="流水号不能为空")
	@Size(max=32,message="流水号长度最大32位")
	private String seqId;
	/**
	 * 原交易流水号
	 */
	@NotBlank(message="原交易流水号不能为空")
	@Size(max=32,message="原交易流水号长度最大32位")
	private String orgSeqId;
	/**
	 * 退款金额
	 */
	@NotBlank(message="退款金额不能为空")
	@Size(max=16,message="退款金额长度最大16位")
	private String amount;
	
	/**
	 * 分账信息
	 */
	private String splitBillInfo;
	
	private long discountsAmount;
	
	private List<SharingInfoBO> sharingInfo;

	/**
	 * 采集设备信息
	 * add by weiyubin 2021-12-17
	 */
	/**
	 * 交易设备信息
	 */
	@Valid
	@NotNull(message="终端信息不能为空")
	private TradeTerminalBO tradeTerminalInfo;
	/**
	 * 是否来源SDK(1:是；0：否)
	 */
	private String payaccFlag;
	
	/**
	 * 退款资金来源 2023-11-1
	 */
	@EnumValidator(value= RefundAccontType.class,message="退款资金来源类型错误")
	private String fundsAccount;
	
	/**
	 * 退款结算类型 0：退款发起日结算 ；1：与支付单结算日一致  2024-2-20
	 */
	//@EnumValidator(value= SettlementTypeEnum.class,message="退款结算类型错误")
	//private String settlementType;
}
