package com.leibown.lcfn_library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.leibown.lcfn_library.swipe.OnLoadListener;
import com.leibown.library.widget.status.StatusViewContainer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class SwipeStatusViewContainer extends StatusViewContainer {


    private SmartRefreshLayout smartRefreshLayout;
    private OnLoadListener onLoadListener;

    public SwipeStatusViewContainer(Context context) {
        super(context);
    }

    @Override
    public ViewGroup initContainer() {
        smartRefreshLayout = (SmartRefreshLayout) View.inflate(mContext, R.layout.layout_swipe_status_view_container, null);
        setIsEnableRefresh(enableRefresh);
        setIsEnableLoadMore(enableLoadMore);
        setRootView(smartRefreshLayout);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (onLoadListener != null) {
                    smartRefreshLayout.setNoMoreData(false);
                    onLoadListener.onRefresh();
                }
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (onLoadListener != null)
                    onLoadListener.onLoadMore();
            }
        });
        setOnEmptyClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLoadListener != null)
                    onLoadListener.onEmpty();
            }
        });
        setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLoadListener != null) {
                    onLoadListener.onRetry();
                }
            }
        });
        return smartRefreshLayout.findViewById(R.id.fl_swipe_status_container);
    }

    //是否可以加载更多
    private boolean enableLoadMore = false;
    //是否可以刷新
    private boolean enableRefresh = false;

    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        setIsEnableLoadMore(enableLoadMore);
    }

    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        setIsEnableRefresh(enableRefresh);
    }

    private void setIsEnableLoadMore(boolean enableLoadMore) {
        smartRefreshLayout.setEnableLoadMore(enableLoadMore);
    }

    private void setIsEnableRefresh(boolean enableRefresh) {
        smartRefreshLayout.setEnableRefresh(enableRefresh);
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public OnLoadListener getOnLoadListener() {
        return onLoadListener;
    }

    public void loadComplete() {
        if (smartRefreshLayout.getState() == RefreshState.Refreshing)
            smartRefreshLayout.finishRefresh();
        if (smartRefreshLayout.getState() == RefreshState.Loading)
            smartRefreshLayout.finishLoadMore();
    }

    public void setNoMoreData(boolean isNoMoreData) {
        smartRefreshLayout.setNoMoreData(isNoMoreData);
    }

    @Override
    public void showContent() {
        super.showContent();
        setIsEnableRefresh(enableRefresh);
        setIsEnableLoadMore(enableLoadMore);
        loadComplete();
    }

    @Override
    public void showEmpty() {
        super.showEmpty();
        setIsEnableRefresh(true);
        setIsEnableLoadMore(false);
        loadComplete();
    }

    @Override
    public void showLoading() {
        super.showLoading();
        setIsEnableRefresh(false);
        setIsEnableLoadMore(false);
    }

    @Override
    public void showError() {
        super.showError();
        setIsEnableRefresh(true);
        setIsEnableLoadMore(false);
        loadComplete();
    }


}
