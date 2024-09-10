package com.llf.base;

import com.llf.assertion.AssertionResultCode;

public class NdrException extends BaseException{

    public NdrException(AssertionResultCode errorCodeEnum){
        super(errorCodeEnum);
    }

    public NdrException(AssertionResultCode errorCodeEnum,String message){
        super(errorCodeEnum,message);
    }

    public NdrException(AssertionResultCode errorCodeEnum,String message,Throwable cause){
        super(errorCodeEnum,message,cause);
    }
}
