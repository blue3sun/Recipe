package com.blue33sun.recipe.view.category;

import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.model.category.MenuCategory;
import com.blue33sun.recipe.view.IView;

import java.util.List;

/**
 * ClassName:IMenuCategoryView
 * Description:菜谱分类View
 * Author:lanjing
 * Date:2017/10/4 10:00
 */

public interface IMenuCategoryView extends IView {

    void showMenuCategoriesSuccess(List<MenuCategory> lists);
    void showMenuCategoriesFailure(ErrorInfo error);

}
