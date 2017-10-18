package com.blue33sun.recipe.ui.activity.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.model.category.Category;
import com.blue33sun.recipe.model.category.MenuCategory;
import com.blue33sun.recipe.ui.activity.BaseActivity;
import com.blue33sun.recipe.ui.activity.recipe.RecipeListActivity;
import com.blue33sun.recipe.ui.activity.recipe.RecipeListActivity2;
import com.blue33sun.recipe.ui.adapter.BaseAdapter;
import com.blue33sun.recipe.ui.adapter.category.CategoryListAdapter;
import com.blue33sun.recipe.utils.ActivityUtils;
import com.blue33sun.recipe.widget.Tipslayout;

import java.util.List;

/**
 * ClassName:CategoryListActivity
 * Description:
 * Author:lanjing
 * Date:2017/10/5 10:31
 */

public class CategoryListActivity extends BaseActivity implements BaseAdapter.OnItemClickListener{

    public static final String EXTRA_MENU_CATEGORY = "menuCategory";
    private RecyclerView mRvCategoryList;
    private Tipslayout mTlTipsLayout;
    private CategoryListAdapter mAdapter;
    private MenuCategory mMenuCategory;
    private List<Category> mCategoryList;
//    private XRefreshView mXRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        initView();
        setListener();
        initData();
    }
    private void initView() {
//        mXRefreshView = (XRefreshView)findViewById(R.id.xrefreshview);
        mTlTipsLayout = (Tipslayout)findViewById(R.id.tl_tips_layout);
        mRvCategoryList = (RecyclerView)findViewById(R.id.rv_category_list);
        //解决ScrollView和RecyclerView滑动冲突的问题
        mRvCategoryList.setNestedScrollingEnabled(false);

        //FullyGridLayoutManager lm = new FullyGridLayoutManager(this,3);
        GridLayoutManager lm = new GridLayoutManager(this,3);
        lm.setOrientation(OrientationHelper.VERTICAL);
        mRvCategoryList.setLayoutManager(lm);

        mAdapter = new CategoryListAdapter(this);
        mRvCategoryList.setAdapter(mAdapter);

//        mXRefreshView.setAutoRefresh(false);
//        mXRefreshView.setAutoLoadMore(false);
//        mXRefreshView.setPullRefreshEnable(true);
//        mXRefreshView.setPullLoadEnable(false);
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        mMenuCategory = intent.getParcelableExtra(EXTRA_MENU_CATEGORY);
        if(mMenuCategory != null){
            mCategoryList = mMenuCategory.getList();
            if (mCategoryList!=null && mCategoryList.size()>0){
                mAdapter.setLists(mCategoryList);
                return;
            }
        }
    }

    @Override
    public void onItemClickListener(View v) {
        Category category = (Category) v.getTag();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RecipeListActivity.EXTRA_CATEGORY,category);
        ActivityUtils.goToNextPage(CategoryListActivity.this,RecipeListActivity2.class,bundle,false);

    }
}
