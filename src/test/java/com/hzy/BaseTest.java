package com.hzy;

import org.springframework.boot.test.context.SpringBootTest;

import com.zlpay.service.CashierService;
import com.zlpay.service.impl.CashierServiceImpl;

@SpringBootTest
public class BaseTest {
	public static void main(String[] args) {
//		Map<String,Object> paramsMap = new HashMap<String, Object>();
//		paramsMap.put("orderTitle", "收银台测试");
//		paramsMap.put("outTradeNo", "202505220001");
//		paramsMap.put("totalAmount", 1);
//		String biz_content = JSON.toJSONString(paramsMap);
//		
//		
//		System.out.println(biz_content);
		//-- CP0000059348  17316065612   杜玉龙  147258
		//-- CP0000059260   17636903690  曹操	
		CashierService ser = new CashierServiceImpl();
		String str = ser.createWebPagePayment("收银", "202505220001", 1);
		System.out.println(str);
	}
	
	
}
