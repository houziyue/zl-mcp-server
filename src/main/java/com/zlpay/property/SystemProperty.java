package com.zlpay.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class SystemProperty {
	
	@Value(value = "${uri.zlzf.cashplatform}")
	private String zlzfCashPlatform;
	
	
	@Value(value = "${uri.zlzf.gateway}")
	private String zlzfGateway;
}
