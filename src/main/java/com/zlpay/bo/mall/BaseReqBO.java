package com.zlpay.bo.mall;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.zlpay.constant.Constant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 
 * @Description: 请求参数基类业务对象
 * @author: YULONG.DU
 * @date: 2018年11月8日 下午4:38:31
 */
@Data
public class BaseReqBO {
	
	@NotBlank(message="merchNo " + Constant.ERROR_PARAM_NOTNULL)
	@Size(max=9, message="merchNo " + Constant.ERROR_PARAM_LENGTH)
	private String merchNo;//商户号
	
	@NotBlank(message="msgId " + Constant.ERROR_PARAM_NOTNULL)
	@Size(max=32, message="msgId " + Constant.ERROR_PARAM_LENGTH)
	private String msgId;//商户请求流水
	
	@NotBlank(message="txCode " + Constant.ERROR_PARAM_NOTNULL)
	@Size(max=21, message="txCode " + Constant.ERROR_PARAM_LENGTH)
	private String txCode;//业务编码
	
	private String timestamp;
	
	@Size(max=255, message="op_station " + Constant.ERROR_PARAM_LENGTH)
	private String op_station;

	/**
	 * 交易设备信息
	 */
	@Size(max=1500, message="trxDevcInf " + Constant.ERROR_PARAM_LENGTH)
	private String trxDevcInf;
	
	@Valid //为空不验证，不为空验证对象引用的内容 2025-5-13 hzy
	private TradeTerminalBO tradeTerminalInfo;
}
