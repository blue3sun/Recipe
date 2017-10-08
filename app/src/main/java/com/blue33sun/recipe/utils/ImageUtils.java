package com.blue33sun.recipe.utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * ClassName:ImageUtils
 * Description:图片相关的工具类
 * Author:lanjing
 * Date:2017/10/6 17:06
 */

public class ImageUtils {

    public static Uri getImgUriFromUrl(String imgUrl){
        Uri imageUri = Uri.parse(imgUrl);
        return imageUri;
    }

    public static DraweeController getFrscoImgControler(SimpleDraweeView simpleDraweeView, Uri imageUri){
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //重试之后要加载的图片URl地址
                .setUri(imageUri)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(simpleDraweeView.getController())
                //构建
                .build();
        return controller;
    }

    public static DraweeController getFrscoImgControler(SimpleDraweeView simpleDraweeView,String imageUrl){
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //重试之后要加载的图片URl地址
                .setUri(imageUrl)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(simpleDraweeView.getController())
                //构建
                .build();
        return controller;
    }
}
