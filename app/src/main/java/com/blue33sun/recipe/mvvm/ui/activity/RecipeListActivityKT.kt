package com.blue33sun.recipe.mvvm.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.blue33sun.recipe.R
import com.blue33sun.recipe.databinding.ActivityRecipeListKtBinding
import com.blue33sun.recipe.model.category.Category
import com.blue33sun.recipe.mvp.ui.activity.BaseActivity
import com.blue33sun.recipe.utils.StringUtils

class RecipeListActivityKT: BaseActivity() {
    val EXTRA_CATEGORY = "category"
    val EXTRA_IS_FROM_SEARCH = "is_from_search"
    val EXTRA_SEARCH_MENU_KEY = "menu_key"

    private lateinit var mBinding: ActivityRecipeListKtBinding
    private var mIsFromSearch: Boolean = false
    private var mSearchMenuKey: String?=""
    private var mCategory: Category?=null
    private var mCid: Int?=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntent()
        mBinding = DataBindingUtil.setContentView<ActivityRecipeListKtBinding>(this, R.layout.activity_recipe_list_kt)
        mBinding.swipeToLoadLayout.setOnRefreshListener{

        }

    }

    private fun initIntent() {
//        Intent intent = getIntent();
//        if(intent != null){
//            mIsFromSearch = intent.getBooleanExtra(EXTRA_IS_FROM_SEARCH,false);
//            mSearchMenuKey = intent.getStringExtra(EXTRA_SEARCH_MENU_KEY);
//            if(StringUtils.isNull(mSearchMenuKey)){
//                mIsFromSearch = false;
//            }
//            mCategory = intent.getParcelableExtra(EXTRA_CATEGORY);
//            if(mCategory != null){
//                mCid = StringUtils.formatToInt(mCategory.getId());
//            }
//        }
//        if(mRecipeListPre == null){
//            mRecipeListPre = new RecipeListPre(this);
//        }
//        if(mIsFromSearch){
//            mRecipeListPre.refreshRecipeListByKey(mSearchMenuKey);
//        }else{
//            mRecipeListPre.refreshRecipeListById(mCid);
//        }
//        showWaitting();
        mIsFromSearch = intent.getBooleanExtra(EXTRA_IS_FROM_SEARCH,false);
        mSearchMenuKey = intent.getStringExtra(EXTRA_SEARCH_MENU_KEY);
        if(TextUtils.isEmpty(mSearchMenuKey)){
            mIsFromSearch = false
        }
        mCategory = intent.getParcelableExtra<Category>(EXTRA_CATEGORY);
        if(mCategory != null){
            mCid = StringUtils.formatToInt(mCategory?.id);
        }
    }


}

