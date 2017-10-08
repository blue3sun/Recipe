package com.blue33sun.recipe.ui.adapter.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.model.category.MenuCategory;
import com.blue33sun.recipe.ui.adapter.BaseAdapter;
import com.blue33sun.recipe.utils.StringUtils;

/**
 * ClassName:MenuCategoryAdapter
 * Description:菜谱分类的适配器
 * Author:lanjing
 * Date:2017/10/3 11:15
 */

public class MenuCategoryAdapter extends BaseAdapter<MenuCategory,MenuCategoryAdapter.MenuViewHolder> {

    public MenuCategoryAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_menu_category,parent,false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(itemView);
        return menuViewHolder;
    }


    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        if (mLists == null || position >= mLists.size() || position < 0){
            return;
        }
        MenuCategory menuCategory = mLists.get(position);
        if (menuCategory == null || StringUtils.isNull(menuCategory.getName())){
            holder.tvMenuName.setVisibility(View.GONE);
        }else{
            holder.tvMenuName.setText(menuCategory.getName());
            holder.tvMenuName.setTag(menuCategory);
            holder.tvMenuName.setVisibility(View.VISIBLE);
        }
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public TextView tvMenuName;//菜种类名

        public MenuViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            initView();
            initListener();

        }

        private void initView() {
            tvMenuName = (TextView) itemView.findViewById(R.id.tv_menu_category_name);
        }
        private void initListener() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null){
                        mOnItemClickListener.onItemClickListener(v);
                    }
                }
            });
        }
    }
}
