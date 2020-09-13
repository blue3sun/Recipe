package com.blue33sun.recipe.mvp.presenter.category;

import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.http.manager.DataManager;
import com.blue33sun.recipe.model.category.MenuCategories;
import com.blue33sun.recipe.model.category.MenuCategory;
import com.blue33sun.recipe.mvp.presenter.Ipresenter;
import com.blue33sun.recipe.utils.HttpUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.blue33sun.recipe.view.category.IMenuCategoryView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName:MenuCategoryPre
 * Description:菜谱种类的Presenter
 * Author:lanjing
 * Date:2017/10/4 9:55
 */

public class MenuCategoryPre implements Ipresenter{

    private IMenuCategoryView mMenuCategoryView;

    public MenuCategoryPre(IMenuCategoryView menuCategoryView) {
        this.mMenuCategoryView = menuCategoryView;
    }

    public void getMenuCategories(){
        Observable<MenuCategories> observable =
                DataManager.getInstance().getMenuCategories(0,"json");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MenuCategories>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MenuCategories value) {
                        if(value != null){
                            if(StringUtils.isEquals(value.getResultcode(),String.valueOf(HttpUtils.CODE_SUCCESS))){
                                if(value.getResult() != null && value.getResult().size() > 0){
                                    List<MenuCategory> result = value.getResult();
                                    mMenuCategoryView.showMenuCategoriesSuccess(result);
                                    return;
                                }
                            }
                        }
                        if(value == null){
                            ErrorInfo error = HttpUtils.getDefaultError();
                            mMenuCategoryView.showMenuCategoriesFailure(error);
                        }else{
                            ErrorInfo error = HttpUtils.getError(value.getError_code(),value.getReason());
                            mMenuCategoryView.showMenuCategoriesFailure(error);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorInfo error = HttpUtils.getExceptionError(e);
                        mMenuCategoryView.showMenuCategoriesFailure(error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
