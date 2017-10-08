package com.blue33sun.recipe.utils;

import com.blue33sun.recipe.MainApplication;
import com.blue33sun.recipe.R;
import com.blue33sun.recipe.http.callback.ErrorInfo;

/**
 * ClassName:HttpUtils
 * Description:Http相关字符串的定义
 * Author:lanjing
 * Date:2017/10/4 21:45
 */

public class HttpUtils {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_EXCEPTION_ERROR = -1;
    public static final int CODE_NO_DATA = 0;

    public static final String MSG_NO_DATA = MainApplication.getInstance().getString(R.string.msg_empty);


    public static ErrorInfo getError(int errorCode, String reason){
        ErrorInfo error = new ErrorInfo(errorCode,reason);
        return error;
    }

    public static ErrorInfo getDefaultError( ){
        ErrorInfo error = new ErrorInfo(CODE_NO_DATA,MSG_NO_DATA);
        return error;
    }

    public static ErrorInfo getExceptionError(Throwable e){
        ErrorInfo error = new ErrorInfo(CODE_EXCEPTION_ERROR,e.getMessage());
        return error;
    }


}
