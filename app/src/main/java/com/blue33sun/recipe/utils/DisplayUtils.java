package com.blue33sun.recipe.utils;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blue33sun.recipe.MainApplication;

/**
 * ClassName:DisplayUtils
 * Description:显示相关的工具类
 * Author:lanjing
 * Date:2017/10/5 15:06
 */

public class DisplayUtils {

    public static void showTv(TextView tv,String message){
        if (StringUtils.isNull(message)){
            tv.setVisibility(View.GONE);
        }else{
            tv.setText(message);
            tv.setVisibility(View.VISIBLE);
        }
    }

    public static void showToast(String messge){
        if(!StringUtils.isNull(messge)){
            Toast.makeText(MainApplication.getInstance(),messge,Toast.LENGTH_LONG).show();
        }
    }

}
