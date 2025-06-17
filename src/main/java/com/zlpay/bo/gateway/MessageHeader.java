/**   
 * Copyright © 2018 zlpay.
 */
package com.zlpay.bo.gateway;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** 
 * @Description: 消息头
 * @author: syuf
 * @date: 2018年7月31日 下午4:34:57  
 */
@Data
public class MessageHeader {
	/**
	 * 消息id
	 */
	@NotBlank(message="msgId不能为空")
	@Size(max=32,min=32,message="msgId长度错误")
	private String msgId;
	/**
	 * 商户编号
	 */
	@NotBlank(message="merchNo不能为空")
	@Size(max=9,min=9,message="merchNo长度错误")
	private String merchNo;
	/**
	 * 业务编号
	 */
	@NotBlank(message="txCode不能为空")
	@Size(max=21,min=12,message="txCode长度错误")
	private String txCode;
	/**
	 * 版本号
	 */
	@NotBlank(message="version不能为空")
	private String version;
	/**
	 * 签名证书编号
	 */
	@NotBlank(message="signNo不能为空")
	private String signNo;
	/**
	 * 是否需要加密 1加密 2不加密
	 */
	@NotBlank(message="encrp不能为空")
	@Size(min=1,max=1,message="encrp长度错误")
	private String encrp;
	/**
	 * 加密证书编号,encrp=1时必填
	 */
	private String encrpNo;
	
	@NotBlank(message="timestamp不能为空")
	@Pattern(regexp="^[0-9]{14}$",message="timestamp格式不正确")
	private String timestamp;
	/**
	 * 是否需要加密 1加签 2不加签
	 */
	//@NotBlank(message="signa不能为空")
	//@Size(min=1,max=1,message="signa长度错误")
	private String signa;
}
