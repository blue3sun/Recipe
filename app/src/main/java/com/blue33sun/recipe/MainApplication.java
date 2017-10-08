package com.blue33sun.recipe;

import android.app.Application;

import com.blue33sun.recipe.utils.Config;
import com.blue33sun.recipe.utils.Constants;
import com.blue33sun.recipe.utils.LogUtils;
import com.blue33sun.recipe.utils.SharedPrefsUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * ClassName:MainApplication
 * Description:自定义Application的子类，主要完成一些初始化的配置
 * Author:lanjing
 * Date:2017/10/3 16:03
 */

public class MainApplication extends Application {
    public static MainApplication mInstance;

    public static MainApplication getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init(){
        initLog();
        initFreso();
        initSharedPref();
    }

    private void initFreso() {
        Fresco.initialize(this);
    }

    private void initLog() {
        LogUtils.DEBUG = true;
    }

    private void initSharedPref() {
        String appkey = SharedPrefsUtils.getStr(Constants.APP_KEY,"");
        if(StringUtils.isNull(appkey)){
            SharedPrefsUtils.putStr(Constants.APP_KEY, Config.APP_KEY);
        }
    }
}
