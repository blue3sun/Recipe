package com.blue33sun.recipe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.blue33sun.recipe.MainApplication;

/**
 * ClassName:SharedPrefsUtils
 * Description:保存与读取App的简单信息
 * Author:lanjing
 * Date:2017/10/3 20:51
 */

public class SharedPrefsUtils {

    private static SharedPreferences mSp;

    public static void initSharedPref() {
        if(mSp == null){
            mSp = MainApplication.getInstance()
                    .getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
    }

    public static String getStr(String key, String defaultValue){
        if(StringUtils.isNull(key)){
           return defaultValue;
        }
        
        initSharedPref();
        String value = mSp.getString(key,defaultValue);
        return value;
    }

    public static void putStr(String key,String value){
        if(StringUtils.isNull(key)){
            return;
        }
        initSharedPref();
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(key,value);
        editor.apply();//这里不用commit()
    }


}
