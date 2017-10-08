package com.blue33sun.recipe.view.recipe;

import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.model.recipe.Recipe;
import com.blue33sun.recipe.view.IView;

import java.util.List;

/**
 * ClassName:IRecipeListView
 * Description:食谱列表的View接口类
 * Author:lanjing
 * Date:2017/10/5 15:19
 */

public interface IRecipeListView extends IView{
    void showRecipeListSuccess(boolean isRefresh,List<Recipe> lists);
    void canLoadMore(boolean isCanLoadMore);
    void showRecipeListFailure(boolean isRefresh,ErrorInfo error);
}
