package com.blue33sun.recipe.ui.activity.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
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
    private boolean mIsFromSearch;//是否来自于搜索页面
    private String mSearchMenuKey;//搜索的菜谱名
    private SwipeToLoadLayout mSwipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        initView();
        setListener();
        initData();
    }
    private void initView() {
        mSwipeToLoadLayout = (SwipeToLoadLayout)findViewById(R.id.swipeToLoadLayout);
        mTlTipsLayout = (Tipslayout)findViewById(R.id.tl_tips_layout);
        mRvRecipeList = (RecyclerView)findViewById(R.id.swipe_target);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvRecipeList.setLayoutManager(lm);

        mAdapter = new RecipeListAdapter(this);
        mRvRecipeList.setAdapter(mAdapter);
    }

    private void setListener() {
        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(mRecipeListPre == null){
                    mRecipeListPre = new RecipeListPre(RecipeListActivity.this);
                }
                if(mIsFromSearch){
                    mRecipeListPre.refreshRecipeListByKey(mSearchMenuKey);
                }else{
                    mRecipeListPre.refreshRecipeListById(mCid);
                }
                showWaitting();
            }
        });
        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(mRecipeListPre == null){
                    mRecipeListPre = new RecipeListPre(RecipeListActivity.this);
                }
                if(mIsFromSearch){
                    mRecipeListPre.loadMoreRecipeListByKey(mSearchMenuKey);
                }else{
                    mRecipeListPre.loadMoreRecipeListById(mCid);
                }
                showWaitting();
            }
        });
        mAdapter.setOnItemClickListener(this);
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
        if(mRecipeListPre == null){
            mRecipeListPre = new RecipeListPre(this);
        }
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
            mSwipeToLoadLayout.setRefreshing(false);
            mAdapter.setLists(lists);
        }else{
            mSwipeToLoadLayout.setLoadingMore(false);
            mAdapter.addLists(lists);
        }

        mTlTipsLayout.showSuccess();
    }

    @Override
    public void canLoadMore(boolean isCanLoadMore) {
        if(isCanLoadMore){
            mSwipeToLoadLayout.setLoadMoreEnabled(true);
        }else{
            mSwipeToLoadLayout.setLoadMoreEnabled(false);
        }
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
