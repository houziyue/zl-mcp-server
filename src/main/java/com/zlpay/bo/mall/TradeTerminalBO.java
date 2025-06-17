package com.zlpay.bo.mall;

import javax.validation.constraints.Size;

import com.zlpay.enums.TerminalEnum;
import com.zlpay.util.EnumValidator;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class TradeTerminalBO {
	/**
	 *IP地址
	 */
	@NotBlank(message="IP地址不能为空")
	@Size(max=32,message="IP地址长度最大32位")
	private String ip;
	/**
	 *终端类型
	 */
	@EnumValidator(value=TerminalEnum.class,message="terminal枚举值非法")
	private String terminal;
	/**
	 *设备MAC
	 */
	@NotBlank(message="交易设备交易设备MAC不能为空")
	@Size(max=20,message="交易设备交易设备MAC长度最大20位")
	private String mac;
	/**
	 *SIM/USIM 卡 ICCID
	 */
	@Size(max=20,message="交易设备IMEI长度最大20位")
	private String imei;
	/**
	 *SIM/USIM 卡IMSI
	 */
	@Size(max=20,message="交易设备IMSI长度最大20位")
	private String imsi;
	/**
	 *WIFI的MAC地址
	 */
	@Size(max=20,message="交易设备WIFI MAC长度最大20位")
	private String wifiMac;
	/**
	 *交易时的设备坐标
	 */
	@Size(max=30,message="交易设备GPS长度最大30位")
	private String gps;
}
