package com.blue33sun.recipe.ui.activity.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.model.category.Category;
import com.blue33sun.recipe.model.recipe.Recipe;
import com.blue33sun.recipe.presenter.recipe.RecipeListPre;
import com.blue33sun.recipe.ui.activity.BaseActivity;
import com.blue33sun.recipe.ui.adapter.BaseAdapter;
import com.blue33sun.recipe.ui.adapter.recipe.RecipeListAdapter;
import com.blue33sun.recipe.utils.ActivityUtils;
import com.blue33sun.recipe.utils.DisplayUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.blue33sun.recipe.view.recipe.IRecipeListView;
import com.blue33sun.recipe.widget.Tipslayout;

import java.util.List;

/**
 * ClassName:RecipeListActivity
 * Description:食谱列表页面
 * Author:lanjing
 * Date:2017/10/5 11:10
 */

public class RecipeListActivity extends BaseActivity implements IRecipeListView, BaseAdapter.OnItemClickListener{

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_IS_FROM_SEARCH = "is_from_search";
    public static final String EXTRA_SEARCH_MENU_KEY = "menu_key";
    private RecyclerView mRvRecipeList;
    private Tipslayout mTlTipsLayout;
    private RecipeListAdapter mAdapter;
    private Category mCategory;
    private int mCid;//标签id
    private RecipeListPre mRecipeListPre;
//    private XRefreshView mXRefreshView;
    private boolean mIsFromSearch;//是否来自于搜索页面
    private String mSearchMenuKey;//搜索的菜谱名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        initView();
        setListener();
        initData();
    }
    private void initView() {
        mTlTipsLayout = (Tipslayout)findViewById(R.id.tl_tips_layout);
        mRvRecipeList = (RecyclerView)findViewById(R.id.rv_recipe_list);

        //解决ScrollView和RecyclerView滑动冲突的问题
        mRvRecipeList.setNestedScrollingEnabled(false);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(OrientationHelper.VERTICAL);
        mRvRecipeList.setLayoutManager(lm);

        mAdapter = new RecipeListAdapter(this);
        mRvRecipeList.setAdapter(mAdapter);

//        mXRefreshView = (XRefreshView)findViewById(R.id.xrefreshview);
//        mXRefreshView.setAutoRefresh(false);
//        mXRefreshView.setAutoLoadMore(false);
//        mXRefreshView.setPullRefreshEnable(true);
//        mXRefreshView.setPullLoadEnable(false);
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(this);
//        mXRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onRefresh(boolean isPullDown) {
//                if (mRecipeListPre != null){
//                    if(mIsFromSearch){
//                        mRecipeListPre.refreshRecipeListByKey(mSearchMenuKey);
//                    }else{
//                        mRecipeListPre.refreshRecipeListById(mCid);
//                    }
//                    showWaitting();
//                }
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//                if (mRecipeListPre != null){
//                    if(mIsFromSearch){
//                        mRecipeListPre.loadMoreRecipeListByKey(mSearchMenuKey);
//                    }else{
//                        mRecipeListPre.loadMoreRecipeListById(mCid);
//                    }
//                    showWaitting();
//                }
//            }
//
//            @Override
//            public void onRelease(float direction) {
//
//            }
//
//            @Override
//            public void onHeaderMove(double headerMovePercent, int offsetY) {
//
//            }
//        });
    }

    private void initData() {
        Intent intent = getIntent();
        if(intent != null){
            mIsFromSearch = intent.getBooleanExtra(EXTRA_IS_FROM_SEARCH,false);
            mSearchMenuKey = intent.getStringExtra(EXTRA_SEARCH_MENU_KEY);
            if(StringUtils.isNull(mSearchMenuKey)){
                mIsFromSearch = false;
            }
            mCategory = intent.getParcelableExtra(EXTRA_CATEGORY);
            if(mCategory != null){
                mCid = StringUtils.formatToInt(mCategory.getId());
            }
        }
        mRecipeListPre = new RecipeListPre(this);
        if(mIsFromSearch){
            mRecipeListPre.refreshRecipeListByKey(mSearchMenuKey);
        }else{
            mRecipeListPre.refreshRecipeListById(mCid);
        }
        showWaitting();
    }
    public void showWaitting(){
        if(mAdapter.getItemCount()==0){
            mTlTipsLayout.showWaiting();
        }else{
            DisplayUtils.showToast(getResources().getString(R.string.msg_waiting));
        }
    }

    @Override
    public void onItemClickListener(View v) {
        Recipe recipe = (Recipe) v.getTag();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RecipeDetailActivity.EXTRA_RECIPE,recipe);
        ActivityUtils.goToNextPage(RecipeListActivity.this,RecipeDetailActivity.class,bundle,false);
    }

    @Override
    public void showRecipeListSuccess(boolean isRefresh, List<Recipe> lists) {
        if(isRefresh){
            mAdapter.setLists(lists);
        }else{
            mAdapter.addLists(lists);
        }
        mTlTipsLayout.showSuccess();
    }

    @Override
    public void canLoadMore(boolean isCanLoadMore) {
//        mXRefreshView.setPullLoadEnable(isCanLoadMore);
    }

    @Override
    public void showRecipeListFailure(boolean isRefresh, ErrorInfo error) {
        if(mAdapter.getItemCount()==0){
            mTlTipsLayout.showFailure(error.getMsg());
        }else{
            DisplayUtils.showToast(error.getMsg());
        }
    }
}
