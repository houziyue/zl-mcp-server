package com.zlpay.enums.payacc;

/**
 * 
 * @Description: 访问类型枚举类
 * @author: lxy
 * @date: 2022年3月8日 下午4:18:08
 */
public enum AccessTypeEnum {
    PAY("01", "收银台"),
    ENTRAUSTED_PAY ("02", "委托代扣款并签约"),
//    QR_PAY("03", "被扫支付"),
    HOME("04", "home页"),
//    BANK_LIST("05", "银行卡列表"),
    AGREEMENT_SIGN("06", "申请免密签约/委托代扣"),
    // SDK易信收银台新增
    MER_PAY("07", "商户收银台"),
    OPEN_ACCOUNT("08", "开户或绑卡"),
    OPEN_PAY_ACCOUNT("09", "开通支付账户"),
    OPEN_PAYMENT_CODE("10", "打开转账收款码")
    ;
    private String value;
    private String displayName;
    
    public String getValue() {
        return this.value;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    
    AccessTypeEnum(String value, String displayName ){
        this.value = value ;
        this.displayName = displayName ;
    }
    
    /**
     * 枚举转换
     */
    public static AccessTypeEnum parseOf(String value){
        for(AccessTypeEnum item : values())
            if(item.getValue().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
    
}
