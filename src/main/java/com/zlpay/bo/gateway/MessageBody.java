/**   
 * Copyright © 2018 zlpay.
 */
package com.zlpay.bo.gateway;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** 
 * @Description: 消息体
 * @author: syuf
 * @date: 2018年8月1日 下午4:18:57  
 */
@Data
public class MessageBody {

	@NotBlank(message="data不能为空")
	private String data;
	/**
	 * 签名
	 */
	@NotBlank(message="sign不能为空")
	private String sign;
	/**
	 * 秘钥
	 */
	@NotBlank(message="secret不能为空")
	private String secret;
	/**
	 * 附件
	 */
	private String attachment;
}
