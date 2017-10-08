package com.blue33sun.recipe.ui.adapter.recipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.model.recipe.Material;
import com.blue33sun.recipe.ui.adapter.BaseAdapter;
import com.blue33sun.recipe.utils.DisplayUtils;

/**
 * ClassName:RecipeListAdapter
 * Description:食谱详情里面的食材、用料适配器
 * Author:lanjing
 * Date:2017/10/5 14:37
 */
public class RecipeDetailMaterialAdapter extends BaseAdapter<Material,RecipeDetailMaterialAdapter.RecipeDetailMaterialViewHolder> {

    public RecipeDetailMaterialAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecipeDetailMaterialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_recipe_detail_material,parent,false);
        RecipeDetailMaterialViewHolder viewHolder = new RecipeDetailMaterialViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeDetailMaterialViewHolder holder, int position) {
        if (mLists == null || position >= mLists.size() || position < 0){
            holder.itemView.setVisibility(View.GONE);
            return;
        }
        Material material = mLists.get(position);
        //显示材料名称
        DisplayUtils.showTv(holder.tvMaterialName,material.getName());
        //显示材料用量
        DisplayUtils.showTv(holder.tvMaterialAmount,material.getAmount());
        //最后一个item不用显示后面的横线
        if(position == (getItemCount()-1)){
            holder.tvViewLineTwo.setVisibility(View.GONE);
        }else{
            holder.tvViewLineTwo.setVisibility(View.VISIBLE);
        }
    }

    public class RecipeDetailMaterialViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private TextView tvMaterialName;
        private TextView tvMaterialAmount;
        private View tvViewLineTwo;

        public RecipeDetailMaterialViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            initView();
        }

        private void initView() {
            tvMaterialName = (TextView)itemView.findViewById(R.id.tv_name);
            tvMaterialAmount = (TextView)itemView.findViewById(R.id.tv_amount);
            tvViewLineTwo = itemView.findViewById(R.id.v_line_two);
        }
    }
}
