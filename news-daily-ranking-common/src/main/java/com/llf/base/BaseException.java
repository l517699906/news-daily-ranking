package com.llf.base;

import com.llf.assertion.AssertionResultCode;

public abstract class BaseException extends RuntimeException {

    /**
     * 异常错误代码
     */
    private AssertionResultCode code;

    public BaseException(AssertionResultCode errorCodeEnum) {
        super();
        this.code = errorCodeEnum;
    }

    public BaseException(AssertionResultCode errorCodeEnum, String message) {
        super(message);
        this.code = errorCodeEnum;
    }

    /**
     * 创建一个<code>BusinessException</code>
     *
     * @param code         错误码
     * @param errorMessage 错误描述
     * @param cause        异常
     */
    public BaseException(AssertionResultCode code, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.code = code;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public AssertionResultCode getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(AssertionResultCode code) {
        this.code = code;
    }

}
