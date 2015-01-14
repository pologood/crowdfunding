package com.youku.share.crowdfunding.util.exception;

/*
 * 系统权限异常
 * 系统在发现用户进行授权外的操作时抛出
 * 建议在filter层使用
 * */
public class AuthorityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6503867028057963202L;

}
