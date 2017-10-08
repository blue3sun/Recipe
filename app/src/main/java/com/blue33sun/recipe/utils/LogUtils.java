package com.blue33sun.recipe.utils;

import android.util.Log;

/**
 * ClassName:LogUtils
 * Description:打印日志
 * Author:lanjing
 * Date:2017/10/3 15:28
 */

public class LogUtils {
    public static final String TAG = "LOG";
    public static boolean DEBUG = true;

    public static void v(String msg){
        if(DEBUG){
            Log.v(TAG, msg);
        }
    }

    public static void i(String msg){
        if(DEBUG){
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg){
        if(DEBUG){
            Log.w(TAG, msg);
        }
    }

    public static void d(String msg){
        if(DEBUG){
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg){
        if(DEBUG){
            Log.e(TAG, msg);
        }
    }

    public static void v(String tag,String msg){
        if(DEBUG){
            if(StringUtils.isNull(tag)){
                tag = TAG;
            }
            Log.v(tag, msg);
        }
    }

    public static void i(String tag,String msg){
        if(DEBUG){
            if(StringUtils.isNull(tag)){
                tag = TAG;
            }
            Log.i(tag, msg);
        }
    }

    public static void w(String tag,String msg){
        if(DEBUG){
            if(StringUtils.isNull(tag)){
                tag = TAG;
            }
            Log.w(tag, msg);
        }
    }

    public static void d(String tag,String msg){
        if(DEBUG){
            if(StringUtils.isNull(tag)){
                tag = TAG;
            }
            Log.d(tag, msg);
        }
    }

    public static void e(String tag,String msg){
        if(DEBUG){
            if(StringUtils.isNull(tag)){
                tag = TAG;
            }
            Log.e(tag, msg);
        }
    }

}

