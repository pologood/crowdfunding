package com.youku.share.crowdfunding.exception;

import com.youku.share.crowdfunding.util.Utils;

public class BaseException extends Exception {

    private String serialNo;
    private Throwable throwable;
    private String code;
    private String message;

    /**
     * 
     */
    private static final long serialVersionUID = 5367899676686245833L;

    public BaseException(Throwable throwable) {
        serialNo = Utils.generateSerialNumber();
        if (throwable == null)
            return;
        this.throwable = throwable;
        code = throwable.getClass().getName();
        message = throwable.getMessage();
    }
    
    public BaseException(String code, String message){
        this.code = code;
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
