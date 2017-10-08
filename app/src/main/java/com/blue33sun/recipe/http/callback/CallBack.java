package com.blue33sun.recipe.http.callback;

/**
 * ClassName:CallBack
 * Description:网络请求的回调
 * Author:lanjing
 * Date:2017/10/3 20:38
 */

public class CallBack<T> {
    public void onStrat(){}
    public void onSuccess(T t){}
    public void onFailure(ErrorInfo error){}
    public void onProgress(int current,int total){}
}
