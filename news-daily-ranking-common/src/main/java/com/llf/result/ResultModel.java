package com.llf.result;

import com.llf.assertion.AssertionResultCode;
import lombok.Data;
import lombok.ToString;
import org.slf4j.MDC;

@Data
@ToString
public class ResultModel<T> {

    private String requestId;

    private String code;

    public T data;

    public boolean success;

    public String message;

    public ResultModel() {
    }

    public ResultModel(T data, boolean success, AssertionResultCode message) {
        this.requestId = MDC.get("requestId");
        this.code = message.getResultCode();
        this.data = data;
        this.success = success;
        this.message = message.getResultMsg();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> ResultModel<T> success(T model) {
        ResultModel<T> result = new ResultModel<>();
        result.setSuccess(true);
        result.setData(model);
        result.setCode(CommonErrorCode.SUCCESS.getResultCode());
        result.setRequestId(MDC.get("requestId"));
        return result;
    }

    public static <T> ResultModel<T> unSuccess(AssertionResultCode error) {
        ResultModel<T> result = new ResultModel<>();
        result.setRequestId(MDC.get("requestId"));
        result.setSuccess(false);
        result.setCode(error.getResultCode());
        result.setMessage(error.getResultMsg());
        return result;
    }

    public static <T> ResultModel<T> unSuccess(AssertionResultCode error, String errorMsg) {
        ResultModel<T> result = new ResultModel<>();
        result.setRequestId(MDC.get("requestId"));
        result.setSuccess(false);
        result.setCode(error.getResultCode());
        result.setMessage(errorMsg);
        return result;
    }
}
