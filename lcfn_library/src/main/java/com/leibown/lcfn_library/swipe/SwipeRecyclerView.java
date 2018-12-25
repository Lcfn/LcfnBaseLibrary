package com.leibown.lcfn_library.swipe;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.leibown.lcfn_library.R;
import com.leibown.library.widget.status.DefaultStatusView;
import com.leibown.library.widget.status.IStatusViewContainer;
import com.leibown.library.widget.status.StatusViewContainer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by apple on 2018/4/2.
 */

public class SwipeRecyclerView extends LinearLayout implements IStatusViewContainer {

    private final int NomalStatus = 0;
    private final int LoadingStatus = 1;
    private final int EmptyStatus = 2;
    private final int ErrorStatus = 3;

    private int status = 0;

    private Context context;
    private SmartRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private OnLoadListener onLoadListener;
    private StatusViewContainer mStatusContainer;
    private View Contentview;

    public SwipeRecyclerView(Context context) {
        this(context, null);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setOrientation(LinearLayout.VERTICAL);

        LayoutInflater mInflater = LayoutInflater.from(context);
        Contentview = mInflater.inflate(R.layout.view_swipe_recyclerview, null);
        initStatusContainer(context, new StatusViewContainer(context));

        swipeRefreshLayout = Contentview.findViewById(R.id.swiperefreshlayout);
        ClassicsHeader mClassicsHeader = (ClassicsHeader) swipeRefreshLayout.getRefreshHeader();
        mClassicsHeader.setSpinnerStyle(SpinnerStyle.Scale);
//        /设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        //        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        // 设定下拉圆圈的背景:默认white
        // swipeRefreshLayout.setProgressBackgroundColor(android.R.color.white);
        //设置刷新时动画的颜色，可以设置4个


        recyclerView = Contentview.findViewById(R.id.recyclerview);

        swipeRefreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (onLoadListener != null) {
                    swipeRefreshLayout.setNoMoreData(false);
                    onLoadListener.onRefresh();
                }
            }
        });

        swipeRefreshLayout.setOnLoadMoreListener(new com.scwang.smartrefresh.layout.listener.OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (onLoadListener != null)
                    onLoadListener.onLoadMore();
            }
        });
    }

    private void initStatusContainer(Context context, StatusViewContainer statusViewContainer) {
        DefaultStatusView defaultStatusView = new DefaultStatusView(context);

        if (mStatusContainer != null) {
            ((ViewGroup) mStatusContainer.getRootView()).removeAllViews();
            mStatusContainer = null;
        }
        if (statusViewContainer == null)
            mStatusContainer = new StatusViewContainer(context);
        else
            mStatusContainer = statusViewContainer;
        mStatusContainer.setDefaultStatusView(defaultStatusView);
        mStatusContainer.setContentView(Contentview);

        addView(mStatusContainer.getRootView(), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mStatusContainer.showContent();
        mStatusContainer.setOnRetryListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLoadListener != null) {
                    onLoadListener.onRetry();
                }
            }
        });
        mStatusContainer.setOnEmptyClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLoadListener != null) {
                    onLoadListener.onEmpty();
                }
            }
        });
    }


    /**
     * 显示Loading状态
     */
    public void showLoading() {
        mStatusContainer.showLoading();
        status = LoadingStatus;

    }

    /**
     * 显示Empty状态
     */
    public void showEmpty() {
        mStatusContainer.showEmpty();
        status = EmptyStatus;
    }

    /**
     * 显示Retry状态
     */
    public void showRetry() {
        mStatusContainer.showError();
        status = ErrorStatus;
    }

    public void setLoadingText(String loadingText) {
        mStatusContainer.setLoadingText(loadingText);
    }

    public void setEmptyText(String emptyText) {
        mStatusContainer.setEmptyText(emptyText);
    }

    public void setEmptyImgRes(int imgRes) {
        mStatusContainer.setEmptyImgRes(imgRes);
    }


    public void setErrorImgRes(int imgRes) {
        mStatusContainer.setErrorImgRes(imgRes);
    }

    public void setLoadingImgRes(int imgRes) {
        mStatusContainer.setLoadingImgRes(imgRes);
    }

    public void setErrorText(String s) {
        mStatusContainer.setErrorText(s);
    }


    /**
     * 显示内容
     */
    public void showContent() {
        mStatusContainer.showContent();
        status = NomalStatus;
    }

    int left, right, top, bottom = 0;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (left == 0)
            left = getPaddingLeft();
        if (right == 0)
            right = getPaddingRight();
        if (top == 0)
            top = getPaddingTop();
        if (bottom == 0)
            bottom = getPaddingBottom();
        setPadding(0, 0, 0, 0);
        recyclerView.setPadding(left, top, right, bottom);
    }

    public void loadComplete() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.finishRefresh();
        if (swipeRefreshLayout.isLoading())
            swipeRefreshLayout.finishLoadMore();
        if (status != NomalStatus)
            showContent();
    }

    public void setOnLoadListener(OnLoadListener listener) {
        onLoadListener = listener;
    }


    public SmartRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 关闭刷新与加载更多
     */
    public void setDisnable() {
        swipeRefreshLayout.setEnableRefresh(false);
        swipeRefreshLayout.setEnableLoadMore(false);

    }

    public void setEnableRefresh() {
        swipeRefreshLayout.setEnableRefresh(true);
    }

    public void setDisableRefresh() {
        swipeRefreshLayout.setEnableRefresh(false);
    }


    public void setEnableLoadMore() {
        swipeRefreshLayout.setEnableLoadMore(true);
    }


    public void setDisableLoadMore() {
        swipeRefreshLayout.setEnableLoadMore(false);
    }

    /**
     * 打开刷新与加载更多
     */
    public void setEnable() {
        swipeRefreshLayout.setEnableRefresh(true);
        swipeRefreshLayout.setEnableLoadMore(true);
    }

    public void setNoMoreData() {
        swipeRefreshLayout.setNoMoreData(true);
    }

    /**
     * 获取状态view容器
     *
     * @return
     */
    @Override
    public StatusViewContainer getStatusViewContainer() {
        return mStatusContainer;
    }
}