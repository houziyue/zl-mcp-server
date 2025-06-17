package com.zlpay.bo.mall;

import javax.validation.constraints.Size;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** 
 * @Description: 分账信息域
 * @author: hzy
 * @date: 2023年6月6日 下午5:19:31  
 */
@Data
public class SharingInfoBO {
	
	/**
	 *商户号
	 */
	@NotBlank(message="接受方商户号不可空")
	@Size(min=9,max=9,message="接受方商户号长度超限")
	private String merchantCode;
	/**
	 *分账金额
	 */
	@NotBlank(message="分账金额不可空")
	@Size(max=16,message="分账金额长度超限")
	private String amount;
	/**
	 *描述
	 */
	@Size(max=50,message="描述长度超限")
	private String description;
	
}
