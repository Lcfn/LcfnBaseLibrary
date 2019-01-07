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
import com.leibown.lcfn_library.SwipeStatusViewContainer;
import com.leibown.library.widget.status.DefaultStatusView;
import com.leibown.library.widget.status.IStatusViewContainer;
import com.leibown.library.widget.status.StatusViewContainer;

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
    private RecyclerView recyclerView;
    private OnLoadListener onLoadListener;
    private SwipeStatusViewContainer mStatusContainer;
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
        SwipeStatusViewContainer swipeStatusViewContainer = new SwipeStatusViewContainer(context);
        initStatusContainer(context, swipeStatusViewContainer);
        recyclerView = Contentview.findViewById(R.id.recyclerview);
    }

    private void initStatusContainer(Context context, SwipeStatusViewContainer statusViewContainer) {
        DefaultStatusView defaultStatusView = new DefaultStatusView(context);

        if (mStatusContainer != null) {
            removeAllViews();
            mStatusContainer.removeAllViews();
            mStatusContainer = null;
        }
        if (statusViewContainer == null)
            mStatusContainer = new SwipeStatusViewContainer(context);
        else
            mStatusContainer = statusViewContainer;
        mStatusContainer.setDefaultStatusView(defaultStatusView);
        mStatusContainer.setContentView(Contentview);

        addView(mStatusContainer.getRootView(), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mStatusContainer.showContent();
        mStatusContainer.setEnableRefresh(true);
        mStatusContainer.setEnableLoadMore(true);
        mStatusContainer.setOnLoadListener(onLoadListener);
    }

    public void setStatusContainer(Context context, SwipeStatusViewContainer statusViewContainer) {
        if (getStatusViewContainer() instanceof SwipeStatusViewContainer) {
            statusViewContainer
                    .setOnLoadListener(
                            ((SwipeStatusViewContainer) getStatusViewContainer()).getOnLoadListener());
        }
        initStatusContainer(context, statusViewContainer);
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
        mStatusContainer.loadComplete();
        if (status != NomalStatus)
            showContent();
    }

    public void setOnLoadListener(OnLoadListener listener) {
        onLoadListener = listener;
        mStatusContainer.setOnLoadListener(onLoadListener);
    }


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 关闭刷新与加载更多
     */
    public void setDisnable() {
        mStatusContainer.setEnableRefresh(false);
        mStatusContainer.setEnableLoadMore(false);

    }

    public void setEnableRefresh() {
        mStatusContainer.setEnableRefresh(true);
    }

    public void setDisableRefresh() {
        mStatusContainer.setEnableRefresh(false);
    }


    public void setEnableLoadMore() {
        mStatusContainer.setEnableLoadMore(true);
    }


    public void setDisableLoadMore() {
        mStatusContainer.setEnableLoadMore(false);
    }

    /**
     * 打开刷新与加载更多
     */
    public void setEnable() {
        mStatusContainer.setEnableRefresh(true);
        mStatusContainer.setEnableLoadMore(true);
    }

    public void setNoMoreData() {
        mStatusContainer.setNoMoreData();
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