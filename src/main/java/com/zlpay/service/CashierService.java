package com.zlpay.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/*
 * 接口调用需要额外的环境变量信息：
 * AP_APP_ID=B000***                    # 商户号。必需。
 * AP_APP_KEY=MIIE...DZdM=                 # 商户应用私钥。必需。
 * AP_PUB_KEY=MIIB...DAQAB                 # 证联公钥。必需。
 * AP_RETURN_URL=https://success-page      # 网页支付完成后对付款用户展示的「同步结果返回地址」。
 * AP_NOTIFY_URL=https://your-own-server   # 支付完成后，用于告知开发者支付结果的「异步结果通知地址」。
 * AP_ENCRYPTION_ALGO=SM2                 # 签名方式。可选值为 "SM2" 或 "RSA"。缺省值为 "SM2"。
*/
public interface CashierService {
	
	/**
	 * 创建一笔付款订单，返回带有支付链接的 Markdown 文本，该链接在电脑浏览器中打开后会展示支付二维码，用户可扫码支付。本工具适用于桌面网站或电脑客户端。
	 */
	@Tool(name = "createWebPagePayment", description = "创建一笔付款订单，返回带有支付链接的 Markdown 文本，该链接在电脑浏览器中打开后会展示支付二维码，用户可扫码支付。本工具适用于桌面网站或电脑客户端。")
	String createWebPagePayment(@ToolParam(description = "该订单的订单标题") String orderTitle,@ToolParam(description = "创建订单参数-商户订单号") String outTradeNo
			,@ToolParam(description = "该订单的支付金额，以分为单位") long totalAmount);
	/**
	 * 创建一笔付款订单，返回带有支付链接的 Markdown 文本，该链接在手机浏览器中打开后可跳转到证联APP或直接在浏览器中支付。本工具适用于移动网站或移动 App。
	 */
	@Tool(name = "createMobilePayment", description = "创建一笔付款订单，返回带有支付链接的 Markdown 文本，该链接在手机浏览器中打开后可跳转到证联APP或直接在浏览器中支付。本工具适用于移动网站或移动 App。")
	String createMobilePayment(@ToolParam(description = "该订单的订单标题") String orderTitle,@ToolParam(description = "创建订单参数-商户订单号") String outTradeNo
			,@ToolParam(description = "该订单的支付金额，以分为单位") long totalAmount);

	/**
	 * 查询一笔付款订单，并返回带有订单信息的文本。 
	 */
	@Tool(name = "queryPayment", description = "查询一笔付款订单，并返回带有订单信息的文本。 ")
	String queryPayment(@ToolParam(description = "商户订单号") String outTradeNo);
	/**
	 * 对交易发起退款，并返回退款状态和退款金额。
	 */
	@Tool(name = "refundPayment", description = "对交易发起退款，并返回退款状态和退款金额。")
	String refundPayment(@ToolParam(description = "退款请求号") String outRequestNo,@ToolParam(description = "商户订单号") String outTradeNo
			,@ToolParam(description = "退款金额，以分为单位") long refundAmount, @ToolParam(description = "退款原因")String refundReason);
	/**
	 * 查询一笔退款订单，并返回退款状态和退款金额。
	 */
	@Tool(name = "queryRefund", description = "查询一笔退款订单，并返回退款状态和退款金额。")
	String queryRefund(@ToolParam(description = "退款请求号") String outRequestNo,@ToolParam(description = "商户订单号") String outTradeNo);
}
