/**
 * OfflineRechargeStatus.java
 * 版权所有(C) 2013 证联融通电子有限公司 
 * 创建:LiuQ 2013-12-12
 */
package com.zlpay.enums.mall;


/**
 * 
 * @Description: TODO
 * @author: zhaofuxiang
 * @date: 2018年6月25日 上午11:06:42
 */
public enum OrderStatus {

	STATUS_00 ("00","成功"),
	STATUS_01 ("01","失败"),
	STATUS_02 ("02","处理中"),
	STATUS_03 ("03","已撤销"),
    // add by maln 2022/08/12 支付账户SDK新增
    STATUS_04 ("04","待支付");

    private String value;
    private String displayName;
    
    public String getValue() {
        return this.value;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    
    OrderStatus(String value, String displayName){
        this.value = value ;
        this.displayName = displayName ;
    }
    
    /**
     * 枚举转换
     */
    public static OrderStatus parseOf(String value){
        for(OrderStatus item : values())
            if(item.getValue().equals(value))
                return item;
        
        throw new IllegalArgumentException("异常错误代码["+value+"]不匹配!");
    }
}
