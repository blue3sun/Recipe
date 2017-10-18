package com.blue33sun.recipe.ui.activity.recipe;

import android.os.Bundle;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.blue33sun.recipe.R;
import com.blue33sun.recipe.ui.activity.BaseActivity;
import com.blue33sun.recipe.utils.DisplayUtils;

import static com.blue33sun.recipe.R.id.swipeToLoadLayout;

/**
 * ClassName:RecipeListActivity2
 * Description:测试页
 * Author:lanjing
 * Date:2017/10/5 11:10
 */

public class RecipeListActivity2 extends BaseActivity{

    private SwipeToLoadLayout mSwipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list2);
        initView();
        setListener();
    }
    private void initView() {
        mSwipeToLoadLayout = (SwipeToLoadLayout)findViewById(swipeToLoadLayout);
    }

    private void setListener() {
        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                DisplayUtils.showToast("refresh.....");
                mSwipeToLoadLayout.setRefreshing(false);
            }
        });
        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                DisplayUtils.showToast("load more.....");
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        });
    }

}
