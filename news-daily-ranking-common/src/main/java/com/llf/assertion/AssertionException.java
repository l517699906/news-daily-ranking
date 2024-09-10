package com.llf.assertion;

/**
 * 断言Util异常基类。所有使用AssertUtil的异常对象，都需要继承此对象。
 *
 * <p>注意：子类继承此异常对象，必须实现默认构造方法，和带异常描述参数的构造方法。即：XXXX(String msg)。
 * <p>另外，子类中使用的结果码，必须实现AssertionResultCode接口。
 */
public abstract class AssertionException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7820674757672180049L;

    // ~~~ 抽象方法

    /**
     * 设置错误枚举信息
     *
     * @param resultCode 错误码
     */
    public abstract void setResultCode(AssertionResultCode resultCode);

    // ~~~ 构造方法

    /**
     * 构造一个<code>AssertionException</code>对象。
     */
    public AssertionException() {
        // 默认构造函数
    }

    /**
     * 构造一个<code>AssertionException</code>对象。
     *
     * @param msg 异常描述
     */
    public AssertionException(String msg) {
        super(msg);
    }

    /**
     * 构造一个<code>AssertionException</code>对象。
     *
     * @param cause 异常原因
     */
    public AssertionException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个<code>AssertionException</code>对象。
     *
     * @param msg   异常描述
     * @param cause 异常原因
     */
    public AssertionException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
