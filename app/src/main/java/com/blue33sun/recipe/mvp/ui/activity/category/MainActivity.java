package com.blue33sun.recipe.mvp.ui.activity.category;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.model.category.MenuCategory;
import com.blue33sun.recipe.mvp.presenter.category.MenuCategoryPre;
import com.blue33sun.recipe.mvp.ui.activity.BaseActivity;
import com.blue33sun.recipe.mvp.ui.activity.recipe.RecipeListActivity;
import com.blue33sun.recipe.mvp.ui.adapter.BaseAdapter;
import com.blue33sun.recipe.mvp.ui.adapter.category.MenuCategoryAdapter;
import com.blue33sun.recipe.utils.ActivityUtils;
import com.blue33sun.recipe.utils.DisplayUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.blue33sun.recipe.view.category.IMenuCategoryView;
import com.blue33sun.recipe.widget.Tipslayout;

import java.util.List;

public class MainActivity extends BaseActivity implements IMenuCategoryView,
        View.OnClickListener,BaseAdapter.OnItemClickListener{

    private EditText mEtSearchInput;
    private ImageView mIvSearch;
    private RecyclerView mRvMenuCategories;
    private Tipslayout mTlTipsLayout;
    private MenuCategoryPre mMenuCategoryPre;
    private MenuCategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        initData();
    }

    private void initView() {
        mEtSearchInput = (EditText)findViewById(R.id.et_search_input);
        mIvSearch = (ImageView)findViewById(R.id.iv_search);
        mRvMenuCategories = (RecyclerView)findViewById(R.id.rv_menu_categories);
        mTlTipsLayout = (Tipslayout)findViewById(R.id.tl_tips_layout);
        //FullyGridLayoutManager lm = new FullyGridLayoutManager(this,3);
        GridLayoutManager lm = new GridLayoutManager(this,3);
        lm.setOrientation(OrientationHelper.VERTICAL);
        mRvMenuCategories.setLayoutManager(lm);
        mAdapter = new MenuCategoryAdapter(this);
        mRvMenuCategories.setAdapter(mAdapter);
    }

    private void setListener() {
        mIvSearch.setOnClickListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    private void initData() {
        mMenuCategoryPre = new MenuCategoryPre(this);
        mMenuCategoryPre.getMenuCategories();
        mTlTipsLayout.showWaiting("");
    }


    @Override
    public void showMenuCategoriesSuccess(List<MenuCategory> lists) {
        mAdapter.setLists(lists);
        mTlTipsLayout.showSuccess();
    }

    @Override
    public void showMenuCategoriesFailure(ErrorInfo error) {
        mTlTipsLayout.showFailure(error.getMsg());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_search://搜索
                String searchMenuKey = mEtSearchInput.getText().toString().trim();
                if(StringUtils.isNull(searchMenuKey)){
                    DisplayUtils.showToast(getResources().getString(R.string.input_menu_empty));
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(RecipeListActivity.EXTRA_IS_FROM_SEARCH,true);
                    bundle.putString(RecipeListActivity.EXTRA_SEARCH_MENU_KEY,searchMenuKey);
                    ActivityUtils.goToNextPage(MainActivity.this,RecipeListActivity.class,bundle,false);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClickListener(View v) {
        MenuCategory menuCategory = (MenuCategory)v.getTag();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CategoryListActivity.EXTRA_MENU_CATEGORY,menuCategory);
        ActivityUtils.goToNextPage(MainActivity.this,CategoryListActivity.class,bundle,false);
    }
}
