package com.blue33sun.recipe.utils;

import android.text.TextUtils;

/**
 * ClassName:StringUtils
 * Description:
 * Author:lanjing
 * Date:2017/10/3 8:01
 */

public class StringUtils {
    /**
     * 判断str是否为空
     * @param str
     * @return
     */
    public static boolean isNull(String str){
        if (TextUtils.isEmpty(str)){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isEquals(String str1,String str2){
        if (!isNull(str1) && !isNull(str2)){
            if(str1.equals(str2)){
                return true;
            }
        }
        return false;
    }
    public static String formatFromInt(int value){
        try{
            String result = String.valueOf(value);
            return result;
        }catch(Exception e){
            LogUtils.v(e.getMessage());
            return null;
        }

    }
    public static int formatToInt(String value){
        try{
            int result = Integer.valueOf(value);
            return result;
        }catch(Exception e){
            LogUtils.v(e.getMessage());
            return 0;
        }

    }

}
