package com.zlpay.enums.payacc;

/**
 * @Description:禁用信用卡标识枚举
 * @Auther: maln
 * @Date: 2023/11/13 14:08
 */
public enum LimitPayEnum {

    CLOSE ("0","关闭信用卡"),
    OPEN ("1","开启信用卡"),
    ;
    private String value;
    private String displayName;

    LimitPayEnum(String value,String displayName ){
        this.value =value;
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static LimitPayEnum parse(String value){
        for (LimitPayEnum item : LimitPayEnum.values()){
            if (item.getValue().equals(value)){
                return item;
            }
        }
        throw new IllegalArgumentException("枚举值["+value+"]不匹配");
    }
}
