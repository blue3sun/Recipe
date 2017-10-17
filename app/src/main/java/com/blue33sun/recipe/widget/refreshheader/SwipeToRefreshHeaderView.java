package com.blue33sun.recipe.widget.refreshheader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.blue33sun.recipe.R;

/**
 * ClassName:SwipeToRefreshHeaderView
 * Description:下拉刷新的自定义头部控件
 * Author:lanjing
 * Date:2017/10/17 19:20
 */

public class SwipeToRefreshHeaderView extends RelativeLayout implements SwipeTrigger,SwipeRefreshTrigger{
    private Context mContext;
    private ProgressBar mPbRefresh;
    private TextView mTvRefreshTip;

    public SwipeToRefreshHeaderView(Context context) {
        this(context,null);
    }

    public SwipeToRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeToRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View refreshHeaderView = layoutInflater.inflate(R.layout.layout_swipe_to_refresh_header,null);
        mPbRefresh = (ProgressBar)refreshHeaderView.findViewById(R.id.pb_refresh);
        mTvRefreshTip = (TextView)refreshHeaderView.findViewById(R.id.tv_refresh_tip);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        addView(refreshHeaderView,lp);
    }

    @Override
    public void onRefresh() {
        mPbRefresh.setVisibility(View.VISIBLE);
        mTvRefreshTip.setText(getResources().getString(R.string.refresh_header_refreshing));
    }

    @Override
    public void onPrepare() {
        mPbRefresh.setVisibility(View.GONE);
        mTvRefreshTip.setText(getResources().getString(R.string.refresh_header_start));
    }

    @Override
    public void onSwipe(int i, boolean b) {
        mPbRefresh.setVisibility(View.GONE);
        mTvRefreshTip.setText(getResources().getString(R.string.refresh_header_swipe));
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void complete() {
        mPbRefresh.setVisibility(View.GONE);
        mTvRefreshTip.setText(getResources().getString(R.string.refresh_header_complete));
    }

    @Override
    public void onReset() {

    }
}
