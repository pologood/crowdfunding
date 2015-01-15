package com.youku.share.crowdfunding.exception;

import com.youku.share.crowdfunding.util.Utils;

/*
 * 程序执行的异常
 * 在controller层包装程序本身发生的异常
 * 在controller层使用
 * */
public class PlatformException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3394180227584580854L;
	
	public PlatformException(){
		super(Utils.generateSerialNumber(),"系统出错");
	}
}
 