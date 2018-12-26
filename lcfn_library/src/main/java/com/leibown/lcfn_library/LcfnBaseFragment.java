package com.leibown.lcfn_library;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leibown.lcfn_library.swipe.OnLoadListener;
import com.leibown.library.MultifunctionalFragment;
import com.leibown.library.widget.status.StatusViewContainer;

/**
 * Created by leibown on 2018/10/8.
 */

public abstract class LcfnBaseFragment extends MultifunctionalFragment implements View.OnClickListener {

    private View actionBar;
    public ImageView iv_back;

    public TextView tv_title;

    public TextView tv_right;

    public ImageView tv_righ_img;


    @Override
    public void bindViews(Bundle savedInstanceState) {

        SwipeStatusViewContainer swipeStatusViewContainer = new SwipeStatusViewContainer(getContext());
        swipeStatusViewContainer.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onRefresh() {
                LcfnBaseFragment.this.onRefresh();
            }

            @Override
            public void onLoadMore() {
                LcfnBaseFragment.this.onLoadMore();
            }

            @Override
            public void onRetry() {
                onReTry();
            }

            @Override
            public void onEmpty() {
                LcfnBaseFragment.this.onEmpty();
            }
        });

        setStatusContainer(swipeStatusViewContainer);

        getContentView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bg_f2));
        actionBar = View.inflate(getContext(), R.layout.layout_actionbar, null);
        //设置ActionBar，传入ActionBar布局
        setActionBar(actionBar);
        setActionBarBackgroudColor(ContextCompat.getColor(getContext(), R.color.white));
        setStatusBarBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        hideActionBar();

        iv_back = getContentView().findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = getContentView().findViewById(R.id.tv_title);
        tv_title.setOnClickListener(this);
        tv_right = getContentView().findViewById(R.id.tv_right);
        tv_right.setOnClickListener(this);
        tv_righ_img = getContentView().findViewById(R.id.tv_righ_img);
        tv_righ_img.setOnClickListener(this);


//        unbinder = ButterKnife.bind(this, getContentView());

        ButterKnifeInit(getContentView());

        tv_righ_img.setVisibility(ShowRightImg() ? View.VISIBLE : View.GONE);
        tv_right.setVisibility(ShowRightText() ? View.VISIBLE : View.GONE);
        hideStatusBar();
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

    public void setNoMoreData() {
        if (getStatusViewContainer() instanceof SwipeStatusViewContainer)
            ((SwipeStatusViewContainer) getStatusViewContainer()).setNoMoreData();
    }


    public void onLoadMore() {

    }

    public void onReTry() {
        loadData();
    }

    public void onEmpty() {
        loadData();
    }

    public void onRefresh() {
        loadData();
    }


    public abstract void ButterKnifeInit(View view);

    public abstract void unbindButterKnife();

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_back) {
            Click_Back(view);

        } else if (i == R.id.tv_title) {
            Click_Title(view);

        } else if (i == R.id.tv_right) {
            Click_Right_Text(view);

        } else if (i == R.id.tv_righ_img) {
            Click_Right_Img(view);

        }
    }

    protected void hideBackBtn() {
        iv_back.setVisibility(View.GONE);
    }

    /**
     * 返回的点击事件
     *
     * @param view
     */
    public void Click_Back(View view) {


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
    public void Click_Right_Text(View view) {

    }

    /**
     * 右边的ImgView 的点击事件
     *
     * @param view
     */
    public void Click_Right_Img(View view) {

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


    protected abstract void initViews();

    protected abstract void loadData();

    public void setTitle(String title) {
        if (actionBar != null) {
            ((TextView) actionBar.findViewById(R.id.tv_title)).setText(title);
        }
    }

    public void setTitleColor(@ColorInt int id) {
        if (actionBar != null) {
            ((TextView) actionBar.findViewById(R.id.tv_title)).setTextColor(id);
        }
    }

    public void setTitle(int resId) {
        if (actionBar != null) {
            ((TextView) actionBar.findViewById(R.id.tv_title)).setText(resId);
        }
    }

    /**
     * 单位是px         setTitleSize(getResources().getDimension(R.dimen.sp_10));
     *
     * @param size
     */
    public void setTitleSize(float size) {
        if (actionBar != null) {
            ((TextView) actionBar.findViewById(R.id.tv_title)).setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
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
        Intent intent = new Intent(getContext(), classz);
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
        Intent intent = new Intent(getContext(), classz);
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
    public void startNextRequest(Class classz, int requestcode) {

        startNext(null, classz, requestcode);
    }


    public static <T extends LcfnBaseFragment> T newInstans(Class clz) {
        return newInstans(null, clz);
    }


    public static <T extends LcfnBaseFragment> T newInstans(Bundle bundle, Class clz) {
        LcfnBaseFragment baseFragment = null;
        try {
            Object instance = clz.newInstance();
            if (instance instanceof LcfnBaseFragment) {
                baseFragment = (LcfnBaseFragment) instance;
                baseFragment.setArguments(bundle);
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) baseFragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindButterKnife();
    }

    public void showActionBarBottomLine() {
        if (actionBar != null) {
            actionBar.findViewById(R.id.action_bar_bottom_line).setVisibility(View.VISIBLE);
        }
    }

    public void setRightImageRes(int res) {
        if (tv_righ_img != null)
            tv_righ_img.setImageResource(res);
    }
}
