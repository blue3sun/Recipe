package com.blue33sun.recipe.mvp.presenter.recipe;

import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.http.manager.DataManager;
import com.blue33sun.recipe.model.recipe.Recipe;
import com.blue33sun.recipe.model.recipe.RecipeData;
import com.blue33sun.recipe.mvp.presenter.Ipresenter;
import com.blue33sun.recipe.utils.HttpUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.blue33sun.recipe.view.recipe.IRecipeListView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName:MenuCategoryPre
 * Description:食谱列表的Presenter
 * Author:lanjing
 * Date:2017/10/4 9:55
 */

public class RecipeListPre implements Ipresenter{

    private IRecipeListView mRecipeListView;
    private int mStartNum = 0;//数据返回起始下标，默认0
    private final int mPageSize = 10;//数据返回条数（最大30，默认10）
    private boolean mIsRefresh;//是否正在刷新

    public RecipeListPre(IRecipeListView view) {
        this.mRecipeListView = view;
    }

    /**
     * 刷新
     * @param cid
     */
    public void refreshRecipeListById(int cid){
        mStartNum = 0;
        mIsRefresh = true;
        Observable<RecipeData> observable = getObservableById(cid);
        getRecipeList(observable);
    }
    /**
     * 刷新
     * @param key
     */
    public void refreshRecipeListByKey(String key){
        mStartNum = 0;
        mIsRefresh = true;
        Observable<RecipeData> observable = getObservableByKey(key);
        getRecipeList(observable);
    }

    /**
     * 加载更多数据
     * @param cid
     */
    public void loadMoreRecipeListById(int cid){
        mStartNum = mStartNum + mPageSize;
        mIsRefresh = false;
        Observable<RecipeData> observable = getObservableById(cid);
        getRecipeList(observable);
    }

    /**
     * 加载更多数据
     * @param key
     */
    public void loadMoreRecipeListByKey(String key){
        mStartNum = mStartNum + mPageSize;
        mIsRefresh = false;
        Observable<RecipeData> observable = getObservableByKey(key);
        getRecipeList(observable);
    }

    public Observable<RecipeData> getObservableById(int cid){
        Observable<RecipeData> observable =
                DataManager.getInstance().getRecipeListById(
                        cid,
                        "json",
                        StringUtils.formatFromInt(mStartNum),
                        StringUtils.formatFromInt(mPageSize),
                        "1");
        return observable;
    }

    public Observable<RecipeData> getObservableByKey(String key){
        Observable<RecipeData> observable =
                DataManager.getInstance().getRecipeListByKey(
                        key,
                        "json",
                        StringUtils.formatFromInt(mStartNum),
                        StringUtils.formatFromInt(mPageSize),
                        "");
        return observable;
    }

    public void getRecipeList(Observable<RecipeData> observable){
        if( observable == null){
            ErrorInfo error = HttpUtils.getDefaultError();
            mRecipeListView.showRecipeListFailure(mIsRefresh,error);
            return;
        }
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
                                    mRecipeListView.showRecipeListSuccess(mIsRefresh,result);
                                    if(StringUtils.formatToInt(value.getResult().getRn())<mPageSize){
                                        mRecipeListView.canLoadMore(false);
                                    }else{
                                        mRecipeListView.canLoadMore(true);
                                    }
                                    return;
                                }
                            }
                        }
                        if(value == null){
                            ErrorInfo error = HttpUtils.getDefaultError();
                            mRecipeListView.showRecipeListFailure(mIsRefresh,error);
                        }else{
                            ErrorInfo error = HttpUtils.getError(value.getError_code(),value.getReason());
                            mRecipeListView.showRecipeListFailure(mIsRefresh,error);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorInfo error = HttpUtils.getExceptionError(e);
                        mRecipeListView.showRecipeListFailure(mIsRefresh,error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
