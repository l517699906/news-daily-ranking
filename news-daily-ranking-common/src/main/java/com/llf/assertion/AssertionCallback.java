package com.llf.assertion;

/**
 * 断言回调接口，在断言失败以后可以触发一些动作
 */
public interface AssertionCallback {

    /**
     * 断言失败以后可以触发一些动作
     */
    void failAction();

}
