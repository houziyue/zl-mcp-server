package com.zlpay.enums;

/**
 * 终端类型
 * @Description:TODO
 * @author: zhaofuxiang
 * @date: 2020年2月12日 下午2:47:59  
 * @param value
 * @param displayName
 */
public enum TerminalEnum {

	WEB("0","WEB", "3"),
	
	IOS("1","IOS", "2"),

	ANDROID("2","ANDROID", "2"),
	
	OTHER("3","其它", "3"),
	;
	
	private String value;
    private String displayName;
    private String spfValue;
    
    public String getValue() {
        return this.value;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }

    public String getSpfValue() {
        return spfValue;
    }

    TerminalEnum(String value, String displayName, String spfValue){
        this.value = value ;
        this.displayName = displayName ;
        this.spfValue = spfValue;
    }

    public static String getSpfValue(String value) {
        for (TerminalEnum item : TerminalEnum.values()) {
            if (item.getValue().equals(value)) {
                return item.getSpfValue();
            }
        }
        throw new IllegalArgumentException("枚举值[" + value + "]匹配异常");
    }
    

}
