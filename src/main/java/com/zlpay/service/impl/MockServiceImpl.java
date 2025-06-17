package com.zlpay.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zlpay.bo.OrderInfo;
import com.zlpay.bo.UserInfo;
import com.zlpay.constant.Constant;
import com.zlpay.redis.client.RedisClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MockServiceImpl {
	
	@Autowired
	private RedisClient redisClient;
	
	/**
	 * 用户使用手机号注册为证联客户
	 * @return
	 */
	@Tool(name = "registByPhone", description = "用户开户第一步，成为证联用户。注册为证联用户，才能继续开立证联的支付账户。接口会以markdown的格式返回开立成功的用户编号。")
	public String registByPhone(@ToolParam(description = "手机号，格式为长度11位的数字",required = true) String phone) {
		log.info("[开户-注册]入参{}",phone);
		String msg ="";
		String key = Constant.LIST_USER_INFO_PHONE_KEY;
		Map<Object, Object> caches= redisClient.hmget(key);
		Map<String,Object> inputCaches = new HashMap<String, Object>();
		if(null == caches) {
			caches = new HashMap<Object, Object>();
		}
		String userId="";
		Iterator<Entry<Object, Object>> it= caches.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String enKey = (String)entry.getKey();
			String enVal = (String)entry.getValue();
			if(enKey.equals(phone)) {
				userId = enVal;
			}
			inputCaches.put(enKey, enVal);
		}
		if(StringUtils.isBlank(userId)) {
			userId = "U"+RandomStringUtils.randomNumeric(8);
		}
		inputCaches.put(phone, userId);
		redisClient.hmset(key, inputCaches);
		
		UserInfo user = new UserInfo();
		user.setPhone(phone);
		user.setUserId(userId);
		user.setBlanceAmt(100000);//初始化1000元
		String userStr = JSON.toJSONString(user);
		redisClient.setString(Constant.LIST_USER_INFO_USERID_KEY+userId, userStr);
		msg = "注册成功，**用户编号*为*"+ userId+"*";
		log.info("[开户-注册]出参{}",msg);
		return msg;
	}
	
	/**
	 * 用户提供名称、证件编号、银行卡号完成实名认证
	 * @return
	 */	
	@Tool(name = "registerByAuthinfo", description = "用户开户第二步，完成实名认证，开立证联支付账户。用户完成第一步注册为证联用户后，才能继续开立证联支付账户。证联支付账户开立成功后，才能做付款、转账等业务。接口会以markdown的格式返回开立成功的用户支付账户编号。")
	public String registerByAuthinfo(@ToolParam(description = "用户编号",required = true) String userId,@ToolParam(description = "用户名称",required = true) String userName,
	@ToolParam(description = "用户证件编号，格式为长度18位的数字",required = true) String certId,@ToolParam(description = "用户银行卡号码，，格式为长度大于4位的数字",required = true) String cardNo) {
		log.info("[开户-认证]入参userId[{}],certId[{}]",userId,certId);
		String msg ="";
		String key = Constant.LIST_USER_INFO_USERID_KEY+userId;
		String userJsonStr= redisClient.get(key);
		UserInfo user = JSON.parseObject(userJsonStr,UserInfo.class);
		String custId ="";
		if(null == user) {
			msg="未发现用户";
			
		}else {
			user.setCertId(certId);
			user.setUserName(userName);
			user.setCardNo(cardNo);
			if(StringUtils.isNotBlank(user.getCustId())) {
				custId = user.getCustId();
			}else {
				custId = "CP"+RandomStringUtils.randomNumeric(8);
				user.setCustId(custId);
			}
			String userStr = JSON.toJSONString(user);
			redisClient.setString(Constant.LIST_USER_INFO_USERID_KEY+userId, userStr);
			redisClient.setString(Constant.LIST_USER_INFO_CUSTID_KEY+custId, userStr);
			
			msg="实名认证成功。**支付账户编号**为*"+custId+"*";
		}
		log.info("[开户-认证]出参msg[{}]",msg);
		return msg;
	}
			
	/**
	 * 实名认证通过的用户，发起转账（转出）交易
	 * @return
	 */
//	@Tool(name = "transferOut", description = "用户发起支付账户转账交易，交易方向为付款人的支付账户转出至收款人的银行账号。接口会以markdown的格式返回转账交易的单号和唤醒付款人完成付款的链接地址。")
//	public String transferOut(@ToolParam(description = "付款人支付账户编号",required = true) String payerCustId,@ToolParam(description = "交易金额，以分为单位",required = true) long amount,@ToolParam(description = "收款人名称",required = true) String userName, @ToolParam(description = "收款人手机号码",required = true) String phone
//			,@ToolParam(description = "收款人证件编号",required = true) String certId,@ToolParam(description = "收款人银行卡号码",required = true) String cardNo) {
	@Tool(name = "transferOut", description = "用户发起支付账户转账交易，交易方向为付款人的支付账户转出至收款人的银行账号。接口会以markdown的格式返回转账交易的单号和唤醒付款人完成付款的链接地址。")
	public String transferOut(@ToolParam(description = "付款人支付账户编号。内容需要为证联开立成功的支付账户编号。",required = true) String payerCustId,@ToolParam(description = "交易金额，以分为单位",required = true) long amount,
			@ToolParam(description = "转账收款方名称，需从上下文中采集转账交易的收款方信息。如上下文中不存在，需要用户额外提供。",required = true) String payeeUserName, @ToolParam(description = "转账收款方手机号，格式为长度11位的数字。需从上下文中采集转账交易的收款方信息。如上下文中不存在，需要用户额外提供。",required = true) String payeePhone
			) {
		log.info("[转账-转出]入参payerCustId[{}],amount[{}],phone[{}]",payerCustId,amount,payeePhone);
		String msg ="";
		String orderId="";
		String userStr =(String)redisClient.get(Constant.LIST_USER_INFO_CUSTID_KEY+payerCustId);
		if(StringUtils.isBlank(userStr)) {
			msg ="未实名认证";
		}else {
			UserInfo payer = JSON.parseObject(userStr,UserInfo.class);
			UserInfo payee = new UserInfo();
			payee.setPhone(payeePhone);
			payee.setUserName(payeeUserName);
			orderId="ORD"+RandomStringUtils.randomNumeric(10);
			OrderInfo order = new OrderInfo();
			order.setOrderId(orderId);
			order.setAmount(amount);
			order.setStatus("0");
			order.setPayerInfo(payer);
			order.setPayeeInfo(payee);
			String orderStr = JSON.toJSONString(order);
			redisClient.setString(Constant.LIST_ORDER_INFO_KEY+orderId, orderStr);
			msg = "# 订单详情\r\n"+
					"| 订单编号 |"+orderId+"|\r\n"+
					"| -------- | -------- |\r\n"+
					"| 付款人   |"+payer.getUserName()+"|\r\n"+
					"| 收款人   |"+payee.getUserName()+"|\r\n"+
					"| 订单状态 | 下单成功 |\r\n"+
					"# 后续操作指引\r\n"+
					"*请点击[完成付款](demosdk://index?token=112233)流程\r\n。"+ 
					"请点击[通知"+payeeUserName+"收款](demosdk://index?token=112233)。";
		}
		
		log.info("[转账-转出]出参msg[{}]",msg);
		return msg;
	}
	/**
	 * 实名认证通过的用户，根据订单号，发起收款交易
	 * @return
	 */
	
	@Tool(name = "transferIn", description = "转账交易的收款人，使用订单编号，完成收款。接口会以markdown的格式返回收款的结果信息。")
	public String transferIn(@ToolParam(description = "转账交易的单号",required = true) String orderId) {
		log.info("[转账-转入]入参orderId[{}]",orderId);
		String msg ="";
		String orderStr =(String)redisClient.get(Constant.LIST_ORDER_INFO_KEY+orderId);
		if(StringUtils.isBlank(orderStr)) {
			msg ="**未发现单号*：*用户未下单或者单号已过期，请重新下单。*";
		}else {
			OrderInfo order =JSON.parseObject(orderStr,OrderInfo.class);
			UserInfo payee= order.getPayeeInfo();
			String payeePphone = payee.getPhone();
			
			String keyPhone = Constant.LIST_USER_INFO_PHONE_KEY;
			Map<Object, Object> caches= redisClient.hmget(keyPhone);
			if(null == caches) {
				msg = "# 订单详情\r\n"+
						"| 订单编号 |"+orderId+"|\r\n"+
						"| -------- | -------- |"+
						"| 付款人   |"+order.getPayerInfo().getUserName()+"|\r\n"+
						"| 收款人   |"+order.getPayeeInfo().getUserName()+"|\r\n"+
						"| 订单状态 | 收款失败 |\r\n"+
						"# 后续操作指引\r\n"+
						"**手机号**为*"+payeePphone+"*的用户未完成用户注册，请先完成开户流程。";
			}else {
				String userId="";
				Iterator<Entry<Object, Object>> it= caches.entrySet().iterator();
				while(it.hasNext()) {
					Entry<Object, Object> entry = it.next();
					String enKey = (String)entry.getKey();
					String enVal = (String)entry.getValue();
					if(enKey.equals(payeePphone)) {
						userId = enVal;
						break;
					}
				}
				if(StringUtils.isBlank(userId)) {
					msg = "# 订单详情\r\n"+
							"| 订单编号 |"+orderId+"|\r\n"+
							"| -------- | -------- |"+
							"| 付款人   |"+order.getPayerInfo().getUserName()+"|\r\n"+
							"| 收款人   |"+order.getPayeeInfo().getUserName()+"|\r\n"+
							"| 订单状态 | 收款失败 |\r\n"+
							"# 后续操作指引\r\n"+
							"**手机号**为*"+payeePphone+"*的用户未完成用户注册，请先完成开户流程。";
				}else {
					String keyUserId = Constant.LIST_USER_INFO_USERID_KEY+userId;
					String userJsonStr= redisClient.get(keyUserId);
					UserInfo user = JSON.parseObject(userJsonStr,UserInfo.class);
					if(null == user ||StringUtils.isBlank(user.getCustId())) {
						msg = "# 订单详情\r\n"+
								"| 订单编号 |"+orderId+"|\r\n"+
								"| -------- | -------- |"+
								"| 付款人   |"+order.getPayerInfo().getUserName()+"|\r\n"+
								"| 收款人   |"+order.getPayeeInfo().getUserName()+"|\r\n"+
								"| 订单状态 | 收款失败 |\r\n"+
								"# 后续操作指引\r\n"+
								"**手机号**为*"+payeePphone+"*的用户未完成实名认证，请先完成开户流程。";
					}else {
						order.setStatus("2");
						orderStr = JSON.toJSONString(order);
						redisClient.setString(Constant.LIST_ORDER_INFO_KEY+orderId, orderStr);
						msg = "| 订单编号 |"+orderId+"|\r\n"+
								"| -------- | -------- |"+
								"| 付款人   |"+order.getPayerInfo().getUserName()+"|\r\n"+
								"| 收款人   |"+order.getPayeeInfo().getUserName()+"|\r\n"+
								"| 订单状态 | 收款成功 |";
						;
					}
				}
				
			}
			
		}
		log.info("[转账-转入]出参msg[{}]",msg);
		return msg;
	}
	
	@Tool(name = "queryBlanceAmt", description = "使用在证联开立成功的支付账户编号，查询账户余额。接口会以markdown的格式返回账户的余额信息。")
	public String queryBlanceAmt(@ToolParam(description = "支付账户编号。内容需要为证联开立成功的支付账户编号。",required = true) String custId) {
		log.info("[查询-余额]入参custId[{}]",custId);
		String msg = "**支付账户编号**为*"+custId+"*的余额为100元";
		log.info("[转账-余额]出参msg[{}]",msg);
		return msg;
	}
}
