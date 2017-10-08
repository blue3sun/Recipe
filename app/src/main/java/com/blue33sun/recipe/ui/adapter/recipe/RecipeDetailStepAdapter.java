package com.blue33sun.recipe.ui.adapter.recipe;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.model.recipe.Step;
import com.blue33sun.recipe.ui.adapter.BaseAdapter;
import com.blue33sun.recipe.utils.DisplayUtils;
import com.blue33sun.recipe.utils.ImageUtils;
import com.blue33sun.recipe.utils.StringUtils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * ClassName:RecipeListAdapter
 * Description:食谱详情里面的做法部分适配器
 * Author:lanjing
 * Date:2017/10/5 14:37
 */

public class RecipeDetailStepAdapter extends BaseAdapter<Step,RecipeDetailStepAdapter.RecipeDetailStepViewHolder> {

    public RecipeDetailStepAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecipeDetailStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_recipe_detail_step,parent,false);
        RecipeDetailStepViewHolder viewHolder = new RecipeDetailStepViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeDetailStepViewHolder holder, int position) {
        if (mLists == null || position >= mLists.size() || position < 0){
            holder.itemView.setVisibility(View.GONE);
            return;
        }
        Step step = mLists.get(position);
        DisplayUtils.showTv(holder.tvStep,step.getStep());
        //显示图片
        //显示图片
        String imgUrl = "";
        if(step != null && !StringUtils.isNull(step.getImg())){
            imgUrl = step.getImg();
        }
        Uri imgUri = ImageUtils.getImgUriFromUrl(imgUrl);
        holder.sdwStep.setImageURI(imgUri);
        holder.sdwStep.setController(ImageUtils.getFrscoImgControler(holder.sdwStep,imgUri));
    }

    public class RecipeDetailStepViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private SimpleDraweeView sdwStep;
        private TextView tvStep;

        public RecipeDetailStepViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            initView();
            initListener();
        }

        private void initView() {
            sdwStep = (SimpleDraweeView)itemView.findViewById(R.id.sdw_step);
            tvStep = (TextView)itemView.findViewById(R.id.tv_step);
        }

        private void initListener() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onItemClickListener(v);
                    }
                }
            });
        }
    }
}
