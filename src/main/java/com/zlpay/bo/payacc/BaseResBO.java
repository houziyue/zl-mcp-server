package com.zlpay.bo.payacc;

import com.zlpay.enums.payacc.PayAccRespCodeEnum;

import lombok.Data;

/**
 * 
 * @Description: 应答结果基类对象
 * @author: lxy
 * @date: 2022年3月4日 上午11:47:36
 */
@Data
public class BaseResBO {
	
	/**
	 * 响应码
	 */
	private String resCode;
	
	/**
	 * 描述信息
	 */
	private String resMsg;
	
	/**
	 * 应答业务参数（json串）
	 */
	private String resData;
	private String riskType;

	public BaseResBO(){

	}

	public BaseResBO(String resCode, String resMsg){
		this.resCode = resCode;
		this.resMsg = resMsg;
	}
	
	/**
	 * 
	* @Description: 根据异常枚举设置响应信息    
	* @param resEnum
	* @return void
	* @throws  
	* @author: lxy
	* @date: 2022年3月4日 上午11:47:55
	 */
	public void setResEnum(PayAccRespCodeEnum resEnum) {
		this.setResCode(resEnum.getValue());
		this.setResMsg(resEnum.getDisplayName());
	}

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getRiskType() {
        return riskType;
    }
}
