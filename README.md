MCP（模型上下文协议）已被公认为大模型连接软件应用的标准协议。只要符合MCP标准，所有工具都能即插即用，有效拓宽了大模型的应用边界。

证联支付MCP支付服务能够支持用户在与AI大模型对话中完成支付，实现证联支付与AI的“双向奔赴”。证联智能支付服务旨在扩宽AI的服务边界，让AI实现从提供信息到支付购买的商业闭环。

“证联支付MCP Server”有以下四个特征，使得它能够满足当前大部分智能体的支付需求：

1、MCP协议原生支持：缩短接入时间和开发代码量

2、双端可用：支持在移动端和网页端的支付需求

3、全流程的支付管理：不仅仅是完成支付，还包括查询、退款等后续服务

4、灵活配置选项：满足不同业务场景的不同需求

MCP实现功能：

签约支付：1）发起签约，2）发起解约，3）发起支付，4）发起退款

二维码支付：1）生成支付二维码，2）查询二维码交易状态，3）发起退款

![image-20250617095731505](C:\Users\86188\AppData\Roaming\Typora\typora-user-images\image-20250617095731505.png)

> 关于本工具的更多介绍和使用指南，包括准备收款商户身份等前置流程，请参考[证联支付官网](http://www.zlrt.com.cn/#/) 介绍。

## 2. 使用和配置



要使用工具的大部分支付能力，你需要先成为证联支付开放平台的收款商户，获取商户私钥。 之后，你可以直接在主流的 MCP Client 上使用证联支付 MCP Server：

### 在 Cursor 中使用



在 Cursor 项目中的 `.cursor/mcp.json` 加入如下配置：

```
{
  "mcpServers": {
    "zlpay-mcp": {
      "command": "java",
      "args": [
         "-Dfile.encoding=UTF-8",
         "-jar",
         "zl-mcp-server-0.0.1.jar"
      ],
      "env": {
        "merchantno": "B00000***",
        "privatekey": "M1ncGrvQqep***",
	"publickey": "1057977***",
	"serialno": "MIICrzCCAlSgAwIBAgI***"
      },
      "disabled": false,
      "autoApprove": []
    }
  }
}
```

### 在 Cline 中使用



在你的 Cline 设置中找到 `cline_mcp_settings.json` 配置文件，并加入如下配置：

```
{
  "mcpServers": {
    "zlpay-mcp": {
      "command": "java",
      "args": [
         "-Dfile.encoding=UTF-8",
         "-jar",
         "zl-mcp-server-0.0.1.jar"
      ],
      "env": {
        "merchantno": "B00000***",
        "privatekey": "M1ncGrvQqep***",
	"publickey": "1057977***",
	"serialno": "MIICrzCCAlSgAwIBAgI***"
      },
      "disabled": false,
      "autoApprove": []
    }
  }
}
```

### 在其他 MCP Client 中使用



你也可以在任何其它 MCP Client 中使用。并按下文介绍设置环境参数即可。

### 所有参数



证联支付 MCP Server 通过环境变量接收参数。参数和默认值包括:

```
# 证联支付开放平台配置

merchantno=2014...222                  # 商户在证联申请的应用 ID（APPID）。必需。
privatekey=MIIE...DZdM=                # 商户在证联申请的应用私钥。必需。
publickey=MIIB...DAQAB                 # 用于验证证联支付服务端数据签名的证联支付公钥。必需。
serialno=1212***                       # 商户的私钥证书序列号。

```



## 3. 支持的能力



以下表格列出了所有可用的支付工具能力：

| 名称                      | 描述                                                         | 参数                                                         | 输出                                                         |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `create-mobile-payment`   | 创建一笔证联支付订单，返回带有支付链接的 Markdown 文本，该链接在手机浏览器中打开后可跳转到证联支付或直接在浏览器中支付。本工具适用于移动网站或移动 App。 | - outTradeNo: 商户订单号，最长 64 个字符 - totalAmount: 支付金额，单位：元，最小 0.01 - orderTitle: 订单标题，最长 256 个字符 | - url: 支付链接的 markdown 文本                              |
| `create-web-page-payment` | 创建一笔证联支付订单，返回带有支付链接的 Markdown 文本，该链接在电脑浏览器中打开后会展示支付二维码，用户可扫码支付。本工具适用于桌面网站或电脑客户端。 | - outTradeNo: 商户订单号，最长 64 个字符 - totalAmount: 支付金额，单位：元，最小 0.01 - orderTitle: 订单标题，最长 256 个字符 | - url: 支付链接的 markdown 文本                              |
| `query-payment`           | 查询一笔证联支付订单，并返回带有订单信息的文本               | - outTradeNo: 商户订单号，最长 64 个字符                     | - tradeStatus: 订单的交易状态 - totalAmount: 订单的交易金额 - tradeNo: 证联支付交易号 |
| `refund-payment`          | 对交易发起退款，并返回退款状态和退款金额                     | - outTradeNo: 商户订单号，最长 64 个字符 - refundAmount: 退款金额，单位：元，最小 0.01 - outRequestNo: 退款请求号，最长 64 个字符 - refundReason: 退款原因，最长 256 个字符（可选） | - tradeNo: 证联支付交易号 - refundResult: 退款结果           |
| `query-refund`            | 查询一笔证联支付退款，并返回退款状态和退款金额               | - outRequestNo: 退款请求号，最长 64 个字符 - outTradeNo: 商户订单号，最长 64 个字符 | - tradeNo: 证联支付交易号 - refundAmount: 退款金额 - refundStatus: 退款状态 |

## 5. 如何选择合适的支付方式



在开发过程中，为了让 LLM 能更准确地选择合适的支付方式，建议在 Prompt 中清晰说明你的产品使用场景：

- **网页支付（`create-web-page-payment`）**：适用于用户在电脑屏幕上看到支付界面的场景。如果您的应用或网站主要运行在桌面端（PC），你可以在 Prompt 中说明："我的应用是桌面软件/PC网站，需要在电脑上展示支付二维码"。
- **手机支付（`create-mobile-payment`）**：适用于用户在手机浏览器内发起支付的场景。如果您的应用是手机H5页面或移动端网站，你可以在 Prompt 中说明："我的页面是手机网页，需要直接在手机上唤起证联支付支付"。

我们会在未来提供更多适合 AI 应用的支付方式，敬请期待。

## 6. 注意事项



- 证联支付 MCP 服务目前处于发布早期阶段，相关能力和配套设施正在持续完善中。如有问题反馈、使用体验或建议，欢迎使用 [证联门户网站](https://www.zqpay.com/login/index.html) 。
- 密钥需要安全有效管理，请妥善保管你的私钥。
- 在开发任何使用 MCP Server 的智能体服务，并提供给用户使用时，请了解必要的安全知识，防范 AI 应用特有的 Prompt 攻击、MCP Server 任意命令执行等安全风险。

## 7. 使用协议



本工具是证联支付能力的组成部分。使用期间，请遵守 证联支付相关服务协议及相关商业行为法规。