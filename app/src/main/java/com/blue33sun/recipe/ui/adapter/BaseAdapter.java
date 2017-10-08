package com.blue33sun.recipe.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * ClassName:BaseAdapter
 * Description:适配器基类
 * Author:lanjing
 * Date:2017/10/3 14:59
 */

public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<T> mLists;
    protected OnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<T> getLists() {
        return mLists;
    }

    public void setLists(List<T> mLists) {
        this.mLists = mLists;
        notifyDataSetChanged();
    }

    public void addLists(List<T> lists){
        this.mLists.addAll(lists);
        setLists(mLists);
    }


    @Override
    public int getItemCount() {
        if(mLists == null){
            return 0;
        }else{
            return mLists.size();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(View v);
    }
}
