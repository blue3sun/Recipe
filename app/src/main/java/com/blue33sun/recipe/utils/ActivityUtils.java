package com.blue33sun.recipe.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * ClassName:ActivityUtils
 * Description:页面跳转工具类
 * Author:lanjing
 * Date:2017/10/5 9:53
 */

public class ActivityUtils {

    public static void goToNextPage(Activity currentActivity,Class nextPageClass,boolean isFinish){
        goToNextPage(currentActivity,nextPageClass,null,isFinish);
    }

    public static void goToNextPage(Activity currentActivity, Class nextPageClass, Bundle bundle, boolean isFinish){
        Intent intent  = new Intent(currentActivity,nextPageClass);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        currentActivity.startActivity(intent);
        if(isFinish){
            currentActivity.finish();
        }
    }
}
