package com.llf.assertion;

/**
 * 异常断言结果码。
 */
public interface AssertionResultCode {

    /**
     * 获取结果码对应的Code。
     *
     * @return 结果码
     */
    String getResultCode();

    /**
     * 获取结果码对应的描述信息。
     *
     * @return 描述信息
     */
    String getResultMsg();
}
