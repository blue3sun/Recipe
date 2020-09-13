package com.blue33sun.recipe.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blue33sun.recipe.utils.LogUtils;

import java.util.ArrayList;
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
        this.mLists = new ArrayList<>();
    }

    public List<T> getLists() {
        return mLists;
    }

    public void setLists(List<T> lists) {
        mLists = lists;
        notifyDataSetChanged();
    }

    public void addLists(List<T> lists){
        if(mLists == null){
            mLists = new ArrayList<>();
        }
        mLists.addAll(lists);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(mLists == null){
            LogUtils.e("BaseAdapter  getItemCount():  mLists为null");
            return 0;
        }else{
            LogUtils.e("BaseAdapter  getItemCount():  mLists.size()是"+mLists.size());
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
