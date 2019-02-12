package com.leibown.lcfn_library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leibown.lcfn_library.swipe.OnLoadListener;
import com.leibown.lcfn_library.utils.DisplayUtil;
import com.leibown.lcfn_library.widget.status.StatusViewContainer;

import java.lang.ref.WeakReference;

/**
 * Created by leibown on 2018/10/8.
 */

public abstract class LcfnBaseActivity extends MultifunctionalActivity implements View.OnClickListener {

    private View actionBar;
    public ImageView iv_back;
    public TextView tv_title;
    public TextView tv_right;
    public ImageView tv_righ_img;
    public View bottomline;


    @Override
    public void bindViews(Bundle savedInstanceState) {
        getContentView().setBackgroundColor(ContextCompat.getColor(this, R.color.bg_f2));

        ActivityStackManager.getInstance().addActivity(new WeakReference<LcfnBaseActivity>(this));

        SwipeStatusViewContainer swipeStatusViewContainer = new SwipeStatusViewContainer(this);
        swipeStatusViewContainer.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onRefresh() {
                LcfnBaseActivity.this.onRefresh();
            }

            @Override
            public void onLoadMore() {
                LcfnBaseActivity.this.onLoadMore();
            }

            @Override
            public void onRetry() {
                onReTry();
            }

            @Override
            public void onEmpty() {
                LcfnBaseActivity.this.onEmpty();
            }
        });
        setStatusContainer(swipeStatusViewContainer);

        //设置各种状态时中间显示的图片
        //setStatusImageViewImageResource(R.drawable.android);

//        View statusView = View.inflate(this, R.layout.layout_status, null);
//        //设置各种状态时的View
//        setStatusView(statusView, R.id.tv_status_content);

        actionBar = View.inflate(this, R.layout.layout_actionbar, null);
        TextView textView = actionBar.findViewById(R.id.tv_title);
        textView.setWidth((int) (DisplayUtil.getScreenWidth(this) * 0.5));

        //设置ActionBar，传入ActionBar布局
        setActionBar(actionBar);

        setActionBarBackgroundColor(ContextCompat.getColor(this, R.color.white));
        setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.white));
        showActionBar();

        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
        tv_righ_img = findViewById(R.id.tv_righ_img);


        iv_back.setOnClickListener(this);
        tv_title.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        tv_righ_img.setOnClickListener(this);


        ButterKnifeInit();
        tv_righ_img.setVisibility(ShowRightImg() ? View.VISIBLE : View.GONE);
        tv_right.setVisibility(ShowRightText() ? View.VISIBLE : View.GONE);

        iv_back.setImageResource(R.drawable.icon_back);
        initViews();
        loadData();

    }

    @Override
    public void setStatusContainer(StatusViewContainer statusViewContainer) {
        if (getStatusViewContainer() instanceof SwipeStatusViewContainer
                && statusViewContainer instanceof SwipeStatusViewContainer) {
            ((SwipeStatusViewContainer) statusViewContainer)
                    .setOnLoadListener(
                            ((SwipeStatusViewContainer) getStatusViewContainer()).getOnLoadListener());
        }
        super.setStatusContainer(statusViewContainer);
    }

    public void loadComplete() {
        if (getStatusViewContainer() instanceof SwipeStatusViewContainer)
            ((SwipeStatusViewContainer) getStatusViewContainer()).loadComplete();
    }

    public void setNoMoreData(boolean isNoMoreData) {
        if (getStatusViewContainer() instanceof SwipeStatusViewContainer)
            ((SwipeStatusViewContainer) getStatusViewContainer()).setNoMoreData(isNoMoreData);
    }

    public void onReTry() {
        loadData();
    }

    public void onLoadMore() {

    }

    public void onEmpty() {
        loadData();
    }

    public void onRefresh() {
        loadData();
    }

    public abstract void ButterKnifeInit();

    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_back) {
            Click_Back(view);
        } else if (i == R.id.tv_title) {
            Click_Title(view);

        } else if (i == R.id.tv_right) {
            clickRightText(view);

        } else if (i == R.id.tv_righ_img) {
            clickRightImg(view);
        } else {
        }
    }

    /**
     * 返回的点击事件
     *
     * @param view
     */
    public void Click_Back(View view) {

        finish();
    }

    /**
     * 标题点击事件
     *
     * @param view
     */
    public void Click_Title(View view) {

    }

    /**
     * 右边的 textview 点击事件
     *
     * @param view
     */
    public void clickRightText(View view) {

    }

    /**
     * 右边的ImgView 的点击事件
     *
     * @param view
     */
    public void clickRightImg(View view) {

    }

    /**
     * 默认不显示显示  子类重写可不显示
     *
     * @return
     */

    public boolean ShowRightImg() {
        return false;
    }

    /**
     * 标题栏邮编的不显示
     *
     * @return
     */

    public boolean ShowRightText() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeActivity(new WeakReference<LcfnBaseActivity>(this));
    }

    protected abstract void initViews();

    protected abstract void loadData();

    public void requestData(boolean isFirstReques, int page) {

    }

    public void setTitle(String title) {
        if (actionBar != null) {
            ((TextView) actionBar.findViewById(R.id.tv_title)).setText(title);
        }
    }

    public void setTitle(int resId) {
        if (actionBar != null) {
            ((TextView) actionBar.findViewById(R.id.tv_title)).setText(resId);
        }
    }

    public void hideBackBtn() {
        if (actionBar != null) {
            actionBar.findViewById(R.id.iv_back).setVisibility(View.GONE);
        }
    }

    public void hideActionBarBottomLine() {
        if (actionBar != null) {
            actionBar.findViewById(R.id.action_bar_bottom_line).setVisibility(View.GONE);
        }
    }

    public void showActionBarBottomLine() {
        if (actionBar != null) {
            actionBar.findViewById(R.id.action_bar_bottom_line).setVisibility(View.VISIBLE);
        }
    }


    /**
     * @param classz 下一个Activity
     */
    public void startNext(Class classz) {
        startNext(null, classz);
    }

    /**
     * @param bundle 参数放在bundle
     * @param classz 下一个Activity
     */
    public void startNext(Bundle bundle, Class classz) {
        Intent intent = new Intent(this, classz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 下一个Activity
     *
     * @param bundle      参数放在bundle
     * @param classz      下一个Activity
     * @param requestcode 请求码
     */
    public void startNext(Bundle bundle, Class classz, int requestcode) {
        Intent intent = new Intent(this, classz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestcode);
    }

    /**
     * 下一个Activity
     *
     * @param classz      下一个Activity
     * @param requestcode 请求码
     */
    public void startNext(Class classz, int requestcode) {

        startNext(null, classz, requestcode);
    }


    public void onLogin() {

    }

    public void setRightImageRes(int res) {
        if (tv_righ_img != null)
            tv_righ_img.setImageResource(res);
    }

}