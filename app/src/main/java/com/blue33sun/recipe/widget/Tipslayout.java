package com.blue33sun.recipe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blue33sun.recipe.R;
import com.blue33sun.recipe.utils.StringUtils;

/**
 * ClassName:Tipslayout
 * Description:app显示加载等待框，或者tips
 * Author:lanjing
 * Date:2017/10/2 22:40
 */
public class Tipslayout extends RelativeLayout{
    private Context mContext;
    private ImageView mIvTips;
    private RelativeLayout mRlTips;
    private TextView mTvTips;
    private ProgressBar mProgressBar;

    public Tipslayout(Context context) {
        this(context,null);
    }

    public Tipslayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Tipslayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.layout_tips,null);
        mRlTips = (RelativeLayout)view.findViewById(R.id.rl_tips);
        mTvTips = (TextView)view.findViewById(R.id.tv_tips);
        mIvTips = (ImageView)view.findViewById(R.id.iv_tips);
        mProgressBar = (ProgressBar)view.findViewById(R.id.pb);
        //添加View需要加上Match_Parent的LayoutParam,否则不会占满整个屏幕
        // （且需要给其添加背景颜色为非透明的，不然其他的布局也会显示出来）
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(view,lp);
    }

    /**
     * 显示网络请求成功的视图
     */
    public void showSuccess(){
        this.setVisibility(View.GONE);
    }

    public void showWaiting(){
        showWaiting(mContext.getResources().getString(R.string.msg_waiting));
    }

    /**
     * 显示正在加载的等待框
     * @param waiting 显示正在加载时的提示文字
     */
    public void showWaiting(String waiting){
        this.setVisibility(View.VISIBLE);
        if(StringUtils.isNull(waiting)){
            waiting = mContext.getResources().getString(R.string.msg_waiting);
        }
        mTvTips.setText(waiting);
        mProgressBar.setVisibility(View.VISIBLE);
        mIvTips.setVisibility(View.GONE);
    }

    /**
     * 网络加载失败的提示
     * @param failure
     */
    public void showFailure(String failure){
        this.setVisibility(View.VISIBLE);
        if(StringUtils.isNull(failure)){
            failure = mContext.getResources().getString(R.string.msg_failure);
        }
        mTvTips.setText(failure);
        mIvTips.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * 数据为空的提示
     */
    public void showEmpty(){
        String empty = mContext.getResources().getString(R.string.msg_empty);
        showEmpty(empty);
    }

    /**
     * 数据为空的提示
     * @param empty
     */
    public void showEmpty(String empty){
        this.setVisibility(View.VISIBLE);
        if(StringUtils.isNull(empty)){
            empty = mContext.getResources().getString(R.string.msg_empty);
        }
        mTvTips.setText(empty);
        mIvTips.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

}
