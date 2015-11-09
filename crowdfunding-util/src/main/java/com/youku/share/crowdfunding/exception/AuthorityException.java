package com.youku.share.crowdfunding.exception;

import com.youku.share.crowdfunding.util.Utils;

/*
 * 系统权限异常
 * 系统在发现用户进行授权外的操作时抛出
 * 建议在filter层使用
 * */
public class AuthorityException extends BaseException{

    
    
    /**
     * 
     */
    private static final long serialVersionUID = 6503867028057963202L;

    public AuthorityException(){
        super(Utils.generateSerialNumber(),"权限不足");
    }
    
}
