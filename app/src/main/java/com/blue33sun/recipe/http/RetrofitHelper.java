package com.blue33sun.recipe.http;

import com.blue33sun.recipe.http.manager.UrlManager;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ClassName:RetrofitHelper
 * Description:
 * Author:lanjing
 * Date:2017/10/3 16:41
 */

public class RetrofitHelper{
    private Retrofit mRetrofit;
    private static RetrofitHelper mInstance;
    private RetrofitService mRetrofitService;

    public static RetrofitHelper getInstance(){
        if(mInstance == null){
            synchronized (RetrofitHelper.class){
                if(mInstance == null){
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;
    }

    private RetrofitHelper(){
        init();
    }

    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlManager.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mRetrofitService = mRetrofit.create(RetrofitService.class);
    }

    public RetrofitService getService(){
        return mRetrofitService;
    }

}
