package com.llf.result;

import com.llf.assertion.AssertionResultCode;
import com.llf.base.ErrorLevels;
import com.llf.base.ErrorTypes;

import java.util.HashMap;
import java.util.Map;

public enum CommonErrorCode implements AssertionResultCode {

    /**
     * 通用错误吗
     */
    SUCCESS("0000", ErrorLevels.DEFAULT, ErrorTypes.SYSTEM, "成功"),
    UNKNOWN_EXCEPTION("9999", ErrorLevels.ERROR, ErrorTypes.SYSTEM, "未知异常"),
    RESULT_NULL_EXCEPTION("0100", ErrorLevels.WARN, ErrorTypes.BIZ, "结果为空"),
    LOGIN_ERROR("1000", ErrorLevels.WARN, ErrorTypes.BIZ, "登陆错误"),
    FAILURE("0001", ErrorLevels.ERROR, ErrorTypes.SYSTEM, "操作失败"),
    BIZ_CHECK("2000", ErrorLevels.ERROR, ErrorTypes.BIZ, "业务异常"),
    AUTH_EXCEPTION("3000", ErrorLevels.ERROR, ErrorTypes.BIZ, "认证异常");
    /**
     * 常量代表固定标识
     */
    protected final static String DC = "DC";

    /**
     * 常量代表版本号
     */
    protected final static String VERSION = "0";

    /**
     * 枚举编码
     */
    private final String code;

    /**
     * 错误级别
     */
    private final String errorLevel;

    /**
     * 错误类型
     */
    private final String errorType;

    /**
     * 描述说明
     */
    private final String description;

    CommonErrorCode(String code, String errorLevel, String errorType, String description) {
        this.code = code;
        this.errorLevel = errorLevel;
        this.errorType = errorType;
        this.description = description;
    }

    /**
     * 枚举编码与枚举的映射map
     */
    private static final Map<String, CommonErrorCode> ERR_CODE_MAP = new HashMap<String, CommonErrorCode>();

    static {
        for (CommonErrorCode errorCode : values()) {
            ERR_CODE_MAP.put(errorCode.code, errorCode);
        }
    }

    /**
     * 获取完整的Code
     *
     * @param bizEventCode
     * @return
     */
    public String getFullCode(String bizEventCode) {
        return DC + VERSION + getErrorLevel() + getErrorType() + bizEventCode + getResultCode();
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code 枚举编码
     * @return 错误码枚举
     */
    public static CommonErrorCode getByCode(String code) {
        return ERR_CODE_MAP.get(code);
    }

    /**
     * Getter method for property <tt>errorLevel</tt>.
     *
     * @return property value of errorLevel
     */
    public String getErrorLevel() {
        return errorLevel;
    }

    /**
     * Getter method for property <tt>errorType</tt>.
     *
     * @return property value of errorType
     */
    public String getErrorType() {
        return errorType;
    }

    @Override
    public String getResultCode() {
        return code;
    }

    @Override
    public String getResultMsg() {
        return description;
    }
}
