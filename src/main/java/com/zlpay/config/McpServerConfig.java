package com.zlpay.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zlpay.service.impl.CashierServiceImpl;
import com.zlpay.service.impl.MockServiceImpl;

@Configuration
public class McpServerConfig {
	
	@Autowired
	private MockServiceImpl mockServiceImpl;
	@Autowired
	private CashierServiceImpl cashierServiceImpl;
	@Bean
	public ToolCallbackProvider  cashToolCallbackProvider(){
		return MethodToolCallbackProvider.builder()
				.toolObjects(cashierServiceImpl,mockServiceImpl)
				.build();
		
	}
//	@Bean
//	public ToolCallbackProvider  cashToolCallbackProvider(MockServiceImpl cashierServiceImpl){
//		return MethodToolCallbackProvider.builder()
//				.toolObjects(cashierServiceImpl)
//				.build();
//		
//	}
}
