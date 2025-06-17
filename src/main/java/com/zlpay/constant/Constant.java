package com.zlpay.constant;

public class Constant {
	
	
			/**
			 * # 商户号。必需。
			 */
			public static  final String AP_APP_ID="merchantno" ;                
			/**
			 * # 商户应用私钥。必需。
			 */
			public static  final String AP_APP_KEY="privatekey" ;
			
			public static  final String AP_APP_SERIAL_NO="serialno" ;
			/**
			 * # 证联公钥。必需。
			 */
			public static  final String AP_PUB_KEY="publickey" ;                 
			/**
			 *  # 网页支付完成后对付款用户展示的「同步结果返回地址」。
			 */
			public static  final String AP_RETURN_URL="AP_RETURN_URL" ;     
			/**
			 * # 支付完成后，用于告知开发者支付结果的「异步结果通知地址」。
			 */
			public static  final String AP_NOTIFY_URL="AP_NOTIFY_URL" ;   
			/**
			 *  # 签名方式。可选值为 "SM2" 或 "RSA"。缺省值为 "SM2"。
			 */
			public static  final String AP_ENCRYPTION_ALGO="AP_ENCRYPTION_ALGO";
			
			
			public static final String ERROR_PARAM_NOTNULL = "参数不能为空";
			public static final String ERROR_PARAM_LENGTH = "参数长度错误";
			public static final String ERROR_PARAM_ENUM = "枚举值非法";
			
			public static final String UTF8 = "UTF-8";
			public static final String PAD_NO = "NoPadding";
			public static final String VERSION_NO="1.1.1";
			
			public static final String LIST_USER_INFO_PHONE_KEY="MCP:USERS:PHONE";
			public static final String LIST_USER_INFO_USERID_KEY="MCP:USERS:USERID:";
			public static final String LIST_USER_INFO_CUSTID_KEY="MCP:USERS:CUSTID:";
			public static final String LIST_ORDER_INFO_KEY="MCP:LST:ORDER:";
			
			
			
			

}
