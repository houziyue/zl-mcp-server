package com.zlpay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlpay.bo.CreateOrderBO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class PaymentController {
	
	
	@RequestMapping(value = "mcpserver/info",method = {RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public CreateOrderBO info(@RequestParam(value = "merchantNo")String merchantNo) {
		log.info("[McpServer-测试]入参merchantNo={}",merchantNo);
		CreateOrderBO order = new CreateOrderBO();
		order.setOrderTitle("McpServer信息详情");
		order.setOutTradeNo("aaaabbbb");
		order.setTotalAmount(200l);
		return order ;
	}
	
	@GetMapping(value = "/zlmcp")
	public String index() {
		return "index";
	}
	@GetMapping(value = "/zlmcp/error")
	public String error() {
		return "error";
	}
}