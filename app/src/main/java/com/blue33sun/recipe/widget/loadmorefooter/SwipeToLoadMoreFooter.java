package com.blue33sun.recipe.widget.loadmorefooter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.blue33sun.recipe.R;

/**
 * ClassName:SwipeToLoadMoreFooter
 * Description:上拉加载更多的自定义footer控件
 * Author:lanjing
 * Date:2017/10/17 19:49
 */

public class SwipeToLoadMoreFooter extends RelativeLayout implements SwipeLoadMoreTrigger,SwipeTrigger{
    private Context mContext;
    private ProgressBar mPbLoadMore;
    private TextView mTvLoadMoreTip;

    public SwipeToLoadMoreFooter(Context context) {
        this(context,null);
    }

    public SwipeToLoadMoreFooter(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeToLoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View loadMoreFooterView = layoutInflater.inflate(R.layout.layout_swipe_to_load_more_footer,null);
        mPbLoadMore = (ProgressBar)loadMoreFooterView.findViewById(R.id.pb_load_more);
        mTvLoadMoreTip = (TextView)loadMoreFooterView.findViewById(R.id.tv_load_more_tip);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        addView(loadMoreFooterView,lp);
    }
    @Override
    public void onLoadMore() {
        mPbLoadMore.setVisibility(View.VISIBLE);
        mTvLoadMoreTip.setText(getResources().getString(R.string.load_more_footer_loading));
    }

    @Override
    public void onPrepare() {
        mPbLoadMore.setVisibility(View.GONE);
        mTvLoadMoreTip.setText(getResources().getString(R.string.load_more_footer_start));
    }

    @Override
    public void onSwipe(int i, boolean b) {
        mPbLoadMore.setVisibility(View.GONE);
        mTvLoadMoreTip.setText(getResources().getString(R.string.load_more_footer_swipe));
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void complete() {
        mPbLoadMore.setVisibility(View.GONE);
        mTvLoadMoreTip.setText(getResources().getString(R.string.load_more_footer_complete));
    }

    @Override
    public void onReset() {

    }
}
