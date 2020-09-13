package com.blue33sun.recipe.mvp.ui.activity.recipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.http.callback.ErrorInfo;
import com.blue33sun.recipe.model.recipe.Material;
import com.blue33sun.recipe.model.recipe.Recipe;
import com.blue33sun.recipe.mvp.presenter.recipe.RecipeDetailPre;
import com.blue33sun.recipe.mvp.ui.activity.BaseActivity;
import com.blue33sun.recipe.mvp.ui.adapter.recipe.RecipeDetailMaterialAdapter;
import com.blue33sun.recipe.mvp.ui.adapter.recipe.RecipeDetailStepAdapter;
import com.blue33sun.recipe.utils.DisplayUtils;
import com.blue33sun.recipe.utils.ImageUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.blue33sun.recipe.view.recipe.IRecipeDetailView;
import com.blue33sun.recipe.widget.Tipslayout;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * ClassName:RecipeListActivity
 * Description:食谱详情页面
 * Author:lanjing
 * Date:2017/10/5 11:10
 */

public class RecipeDetailActivity extends BaseActivity implements IRecipeDetailView {

    public static final String EXTRA_RECIPE = "recipe";
    private Tipslayout mTlTipsLayout;
    private RecipeDetailStepAdapter mRecipeDetailStepAdapter;
    private Recipe mRecipe;
    private int mRecipeId;//菜谱id
    private RecipeDetailPre mRecipeDetailPre;
//    private XRefreshView mXRefreshView;
    private SimpleDraweeView sdwAlbum;
    private TextView mTvTags;
    private TextView mTvIntroduce;
    private TextView mTvTipIngredient;
    private RecyclerView mRvIngredient;
    private TextView mTvTipBurden;
    private RecyclerView mRvBurden;
    private TextView mTvTipStep;
    private RecyclerView mRvStep;
    private ArrayList<Material> mIngredientLists;//食材
    private ArrayList<Material> mBurdenLists;//用料
    private RecipeDetailMaterialAdapter mRecipeDetailIngredientAdapter;
    private RecipeDetailMaterialAdapter mRecipeDetailBurdenAdapter;
    private TextView mTvName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        initView();
        setListener();
        initData();
    }

    private void initView() {
        mTlTipsLayout = (Tipslayout)findViewById(R.id.tl_tips_layout);
        sdwAlbum = (SimpleDraweeView)findViewById(R.id.sdw_album);
        mTvName = (TextView)findViewById(R.id.tv_name);
        mTvTags = (TextView)findViewById(R.id.tv_tags);
        mTvIntroduce = (TextView)findViewById(R.id.tv_introduce);
        mTvTipIngredient = (TextView)findViewById(R.id.tv_tip_ingredient);
        mRvIngredient = (RecyclerView)findViewById(R.id.rv_ingredient);
        mTvTipBurden = (TextView)findViewById(R.id.tv_tip_burden);
        mRvBurden = (RecyclerView)findViewById(R.id.rv_burden);
        mTvTipStep = (TextView)findViewById(R.id.tv_tip_step);
        mRvStep = (RecyclerView)findViewById(R.id.rv_step);

        //解决ScrollView和RecyclerView滑动冲突的问题
        mRvIngredient.setNestedScrollingEnabled(false);
        mRvBurden.setNestedScrollingEnabled(false);
        mRvStep.setNestedScrollingEnabled(false);

        LinearLayoutManager lmIngredient = new LinearLayoutManager(this);
        lmIngredient.setOrientation(OrientationHelper.VERTICAL);
        mRvIngredient.setLayoutManager(lmIngredient);

        LinearLayoutManager lmBurden = new LinearLayoutManager(this);
        lmBurden.setOrientation(OrientationHelper.VERTICAL);
        mRvBurden.setLayoutManager(lmBurden);

        LinearLayoutManager lmStep = new LinearLayoutManager(this);
        lmStep.setOrientation(OrientationHelper.VERTICAL);
        mRvStep.setLayoutManager(lmStep);

        mRecipeDetailIngredientAdapter = new RecipeDetailMaterialAdapter(this);
        mRvIngredient.setAdapter(mRecipeDetailIngredientAdapter);

        mRecipeDetailBurdenAdapter = new RecipeDetailMaterialAdapter(this);
        mRvBurden.setAdapter(mRecipeDetailBurdenAdapter);

        mRecipeDetailStepAdapter = new RecipeDetailStepAdapter(this);
        mRvStep.setAdapter(mRecipeDetailStepAdapter);
//        mXRefreshView = (XRefreshView)findViewById(R.id.xrefreshview);
//        mXRefreshView.setAutoRefresh(false);
//        mXRefreshView.setAutoLoadMore(false);
//        mXRefreshView.setPullRefreshEnable(true);
//        mXRefreshView.setPullLoadEnable(false);
    }

    private void setListener() {
//        mXRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onRefresh(boolean isPullDown) {
//                if (mRecipeDetailPre != null){
//                    mRecipeDetailPre.refreshRecipeDetail(mRecipeId);
//                    showWaitting();
//                }
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
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
        if (intent != null){
            mRecipe = intent.getParcelableExtra(EXTRA_RECIPE);
            if(mRecipe != null){
                mRecipeId = StringUtils.formatToInt(mRecipe.getId());
            }
        }
        mRecipeDetailPre = new RecipeDetailPre(this);
        mRecipeDetailPre.refreshRecipeDetail(mRecipeId);
        showWaitting();
    }
    public void showWaitting(){
        if(mRecipeDetailStepAdapter.getItemCount()==0){
            mTlTipsLayout.showWaiting();
        }else{
            DisplayUtils.showToast(getResources().getString(R.string.msg_waiting));
        }
    }

    public void setIngredientList(Recipe recipe){
        if(mIngredientLists == null){
            mIngredientLists = new ArrayList<>();
        }else{
            mIngredientLists.clear();
        }
        if(recipe!=null){
            String ingredients = recipe.getIngredients();
            mIngredientLists = getMaterials(mIngredientLists,ingredients);
        }
        mRecipeDetailIngredientAdapter.setLists(mIngredientLists);
    }
    public void setBurdenList(Recipe recipe){
        if(mBurdenLists == null){
            mBurdenLists = new ArrayList<>();
        }else{
            mBurdenLists.clear();
        }
        if(recipe!=null){
            String burdens = recipe.getBurden();
            mBurdenLists = getMaterials(mBurdenLists,burdens);
        }
        mRecipeDetailBurdenAdapter.setLists(mBurdenLists);
    }


    /**
     *
     * @param lists 用来保存食材、用料的集合
     * @param materials 食材、用料的字符串 eg:食材：鲈鱼,1个;柠檬,2个;红椒,6个 用料：大蒜头,适量;香菜,适量;盐,适量;生姜,适量
     * @return 用来保存食材、用料的集合
     */
    public ArrayList<Material> getMaterials(ArrayList<Material> lists,String materials){
        if(!StringUtils.isNull(materials)){
            String[] materialsArray = materials.split(";");
            if (materialsArray != null && materialsArray.length > 0){
                for (int i = 0; i < materialsArray.length; i++){
                    String materialStr = materialsArray[i];
                    String[] materialArray = materialStr.split(",");
                    if(materialArray != null && materialArray.length == 2){
                        String materialName = materialArray[0];
                        String materialAmount = materialArray[1];
                        Material material = new Material();
                        material.setName(materialName);
                        material.setAmount(materialAmount);
                        lists.add(material);
                    }
                }
            }
        }
        return lists;
    }

    @Override
    public void showRecipeDetailSuccess(boolean isRefresh, Recipe recipe) {
        //显示图片
        String imgUrl = "";
        if(recipe.getAlbums() != null && recipe.getAlbums().size()>0){
            imgUrl = recipe.getAlbums().get(0);
        }
        Uri imgUri = ImageUtils.getImgUriFromUrl(imgUrl);
        sdwAlbum.setImageURI(imgUri);
        sdwAlbum.setController(ImageUtils.getFrscoImgControler(sdwAlbum,imgUri));

        //显示食谱名称
        DisplayUtils.showTv(mTvName,recipe.getTitle());
        //显示食谱tags
        DisplayUtils.showTv(mTvTags,recipe.getTags());
        //显示食谱介绍
        DisplayUtils.showTv(mTvIntroduce,recipe.getImtro());
        //显示食材
        setIngredientList(recipe);
        //显示用料
        setBurdenList(recipe);

        //显示步骤
        mRecipeDetailStepAdapter.setLists(recipe.getSteps());

        mTlTipsLayout.showSuccess();
    }

    @Override
    public void canLoadMore(boolean isCanLoadMore) {
//        mXRefreshView.setPullLoadEnable(isCanLoadMore);
    }

    @Override
    public void showRecipeDetailFailure(boolean isRefresh, ErrorInfo error) {
        if(mRecipeDetailStepAdapter.getItemCount()==0){
            mTlTipsLayout.showFailure(error.getMsg());
        }else{
            DisplayUtils.showToast(error.getMsg());
        }
    }
}
