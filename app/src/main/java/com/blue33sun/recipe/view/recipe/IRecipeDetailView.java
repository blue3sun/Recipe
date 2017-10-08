package com.blue33sun.recipe.view.recipe;

import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.model.recipe.Recipe;
import com.blue33sun.recipe.view.IView;

/**
 * ClassName:IRecipeListView
 * Description:食谱详情的View接口类
 * Author:lanjing
 * Date:2017/10/5 15:19
 */

public interface IRecipeDetailView extends IView{
    void showRecipeDetailSuccess(boolean isRefresh, Recipe recipe);
    void canLoadMore(boolean isCanLoadMore);
    void showRecipeDetailFailure(boolean isRefresh, ErrorInfo error);
}
