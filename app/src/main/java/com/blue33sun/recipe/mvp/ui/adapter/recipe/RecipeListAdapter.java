package com.blue33sun.recipe.mvp.ui.adapter.recipe;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.model.recipe.Recipe;
import com.blue33sun.recipe.mvp.ui.adapter.BaseAdapter;
import com.blue33sun.recipe.utils.DisplayUtils;
import com.blue33sun.recipe.utils.ImageUtils;
import com.blue33sun.recipe.utils.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * ClassName:RecipeListAdapter
 * Description:食谱列表适配器
 * Author:lanjing
 * Date:2017/10/5 14:37
 */

public class RecipeListAdapter extends BaseAdapter<Recipe,RecipeListAdapter.RecipeListViewHolder> {

    public RecipeListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_recipe_list,parent,false);
        RecipeListViewHolder viewHolder = new RecipeListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {
        LogUtils.e("BaseAdapter  onBindViewHolder():  position:"+position);
        if (mLists == null || position >= mLists.size() || position < 0){
            holder.itemView.setVisibility(View.GONE);
            return;
        }
        Recipe recipe = mLists.get(position);
        holder.itemView.setTag(recipe);
        DisplayUtils.showTv(holder.tvTitle,recipe.getTitle());
        DisplayUtils.showTv(holder.tvTag,recipe.getTags());
        //显示图片
        String imgUrl = "";
        if(recipe.getAlbums() != null && recipe.getAlbums().size()>0){
            imgUrl = recipe.getAlbums().get(0);
        }
        Uri imgUri = ImageUtils.getImgUriFromUrl(imgUrl);
        holder.sdwAlbum.setImageURI(imgUri);
        holder.sdwAlbum.setController(ImageUtils.getFrscoImgControler(holder.sdwAlbum,imgUri));
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private SimpleDraweeView sdwAlbum;
        private TextView tvTitle;
        private TextView tvTag;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            initView();
            initListener();
        }

        private void initView() {
            sdwAlbum = (SimpleDraweeView)itemView.findViewById(R.id.sdw_album);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvTag = (TextView)itemView.findViewById(R.id.tv_tags);
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
