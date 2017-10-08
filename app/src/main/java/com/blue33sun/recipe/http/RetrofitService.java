package com.blue33sun.recipe.http;

import com.blue33sun.recipe.model.category.MenuCategories;
import com.blue33sun.recipe.model.recipe.RecipeData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ClassName:RetrofitService
 * Description:定义网络请求的方法
 * Author:lanjing
 * Date:2017/10/3 17:50
 */

public interface RetrofitService {
    /**
     *
     * @param parentId 分类ID，默认全部
     * @param key 聚合数据：当前应用的APPKEY
     * @param dtype 返回数据的格式,xml或json，默认json
     * @return
     */
    @GET("category")
    Observable<MenuCategories> getMenuCatecories(@Query("parentid")int parentId,
                                                 @Query("key")String key,
                                                 @Query("dtype")String dtype);

    /**
     *
     * @param cid 标签ID
     * @param key 聚合数据当前应用的APPKEY
     * @param dataType 返回数据的格式,xml或json，默认json
     * @param pn 数据返回起始下标，默认0
     * @param rn 数据返回条数，最大30，默认10
     * @param format steps字段屏蔽，默认显示，format=1时屏蔽
     * @return
     */
    @GET("index")
    Observable<RecipeData> getRecipeListById(@Query("cid") int cid,
                                             @Query("key")String key,
                                             @Query("dataType") String dataType,
                                             @Query("pn") String pn,
                                             @Query("rn") String rn,
                                             @Query("format") String format);
    /**
     *
     * @param menu 需要查询的菜谱名
     * @param key 聚合数据当前应用的APPKEY
     * @param dataType 返回数据的格式,xml或json，默认json
     * @param pn 数据返回起始下标，默认0
     * @param rn 数据返回条数，最大30，默认10
     * @param albums albums字段类型，1字符串，默认数组
     * @return
     */
    @GET("query.php")
    Observable<RecipeData> getRecipeListByKey(@Query("menu") String menu,
                                         @Query("key")String key,
                                         @Query("dataType") String dataType,
                                         @Query("pn") String pn,
                                         @Query("rn") String rn,
                                         @Query("albums") String albums);
    /**
     *
     * @param id 分类ID，默认全部
     * @param key 聚合数据：当前应用的APPKEY
     * @param dtype 返回数据的格式,xml或json，默认json
     * @return
     */
    @GET("queryid")
    Observable<RecipeData> getRecipeDetail(@Query("id")int id,
                                                 @Query("key")String key,
                                                 @Query("dtype")String dtype);
}
