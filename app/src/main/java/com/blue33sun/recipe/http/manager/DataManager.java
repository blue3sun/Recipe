package com.blue33sun.recipe.http.manager;

import com.blue33sun.recipe.http.RetrofitHelper;
import com.blue33sun.recipe.http.RetrofitService;
import com.blue33sun.recipe.model.category.MenuCategories;
import com.blue33sun.recipe.model.recipe.RecipeData;
import com.blue33sun.recipe.utils.Constants;
import com.blue33sun.recipe.utils.SharedPrefsUtils;

import io.reactivex.Observable;

/**
 * ClassName:DataManager
 * Description:供上层调用网络请求的方法
 * Author:lanjing
 * Date:2017/10/3 16:44
 */

public class DataManager {
    private static DataManager mInstance;

    private RetrofitService mRetrofitService;

    private String mAppkey;

    public static DataManager getInstance(){
        if(mInstance == null){
            synchronized(DataManager.class){
                if(mInstance == null){
                    mInstance = new DataManager();
                }
            }
        }
        return mInstance;
    }

    private DataManager(){
        mRetrofitService = RetrofitHelper.getInstance().getService();
        mAppkey = SharedPrefsUtils.getStr(Constants.APP_KEY,"");
    }

    /**
     *
     * @param parentId 分类ID，默认全部
     * @param dataType 返回数据的格式,xml或json，默认json
     */
    public Observable<MenuCategories> getMenuCategories(int parentId,String dataType){
        return mRetrofitService.getMenuCatecories(parentId,mAppkey,dataType);
    }

    /**
     *
     * @param cid 标签ID
     * @param dataType 返回数据的格式,xml或json，默认json
     * @param pn 数据返回起始下标，默认0
     * @param rn 数据返回条数，最大30，默认10
     * @param format steps字段屏蔽，默认显示，format=1时屏蔽
     * @return
     */
    public Observable<RecipeData> getRecipeListById(int cid, String dataType, String pn, String rn, String format){
        return mRetrofitService.getRecipeListById(cid,mAppkey,dataType,pn,rn,format);
    }

    /**
     *
     * @param menu 需要查询的菜谱名
     * @param menu 聚合数据当前应用的APPKEY
     * @param dataType 返回数据的格式,xml或json，默认json
     * @param pn 数据返回起始下标，默认0
     * @param rn 数据返回条数，最大30，默认10
     * @param albums albums字段类型，1字符串，默认数组
     * @return
     */
    public Observable<RecipeData> getRecipeListByKey(String menu, String dataType,String pn,String rn,String albums){
        return mRetrofitService.getRecipeListByKey(menu,mAppkey,dataType,pn,rn,albums);
    }


    /**
     *
     * @param id 菜谱的ID
     * @param dataType 返回数据的格式,xml或json，默认json
     */
    public Observable<RecipeData> getRecipeDetail(int id,String dataType){
        return mRetrofitService.getRecipeDetail(id,mAppkey,dataType);
    }

}
