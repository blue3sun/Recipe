package com.blue33sun.recipe.presenter.recipe;

import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.http.manager.DataManager;
import com.blue33sun.recipe.model.recipe.Recipe;
import com.blue33sun.recipe.model.recipe.RecipeData;
import com.blue33sun.recipe.presenter.Ipresenter;
import com.blue33sun.recipe.utils.HttpUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.blue33sun.recipe.view.recipe.IRecipeDetailView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName:MenuCategoryPre
 * Description:食谱详情的Presenter
 * Author:lanjing
 * Date:2017/10/4 9:55
 */

public class RecipeDetailPre implements Ipresenter{

    private IRecipeDetailView mIRecipeDetailView;
    private int mStartNum = 0;//数据返回起始下标，默认0
    private final int mPageSize = 10;//数据返回条数（最大30，默认10）
    private boolean mIsRefresh;//是否正在刷新

    public RecipeDetailPre(IRecipeDetailView view) {
        this.mIRecipeDetailView = view;
    }

    /**
     * 刷新
     * @param id 菜谱id
     */
    public void refreshRecipeDetail(int id){
        mStartNum = 0;
        mIsRefresh = true;
        getRecipeDetail(id);
    }


    public void getRecipeDetail(int id){
        Observable<RecipeData> observable =
                DataManager.getInstance().getRecipeDetail(
                        id,
                        "json");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecipeData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecipeData value) {
                        if(value != null){
                            if(StringUtils.isEquals(value.getResultcode(),StringUtils.formatFromInt(HttpUtils.CODE_SUCCESS))){
                                if(value.getResult() != null && value.getResult().getData() != null
                                        && value.getResult().getData().size()>0){
                                    List<Recipe> result = value.getResult().getData();
                                    mIRecipeDetailView.showRecipeDetailSuccess(mIsRefresh,result.get(0));
                                    mIRecipeDetailView.canLoadMore(false);
                                    return;
                                }
                            }
                        }
                        if(value == null){
                            ErrorInfo error = HttpUtils.getDefaultError();
                            mIRecipeDetailView.showRecipeDetailFailure(mIsRefresh,error);
                        }else{
                            ErrorInfo error = HttpUtils.getError(value.getError_code(),value.getReason());
                            mIRecipeDetailView.showRecipeDetailFailure(mIsRefresh,error);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorInfo error = HttpUtils.getExceptionError(e);
                        mIRecipeDetailView.showRecipeDetailFailure(mIsRefresh,error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
