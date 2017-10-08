package com.blue33sun.recipe.http.callback;

/**
 * ClassName:ErrorInfo
 * Description:封装网络请求的错误信息
 * Author:lanjing
 * Date:2017/10/3 20:46
 */

public class ErrorInfo {
    private int errorCode;
    private String msg;

    public ErrorInfo() {
    }

    public ErrorInfo(int errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
