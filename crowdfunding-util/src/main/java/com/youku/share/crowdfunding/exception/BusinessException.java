package com.youku.share.crowdfunding.exception;

import com.youku.share.crowdfunding.util.Utils;

/*
 * 系统业务异常
 * 系统在发现用户不符合业务规则的操作时抛出
 * 建议在service层使用
 * */
public class BusinessException extends BaseException{

	private String businessRuleCode;
	private boolean forwardToSelf = false;
	private String targetURL;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1535457929165095468L;
	
	public BusinessException(){
		super(Utils.generateSerialNumber(),"违反业务规则");
	}
}
