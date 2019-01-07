package com.leibown.lcfncommonlibrary;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;

import com.leibown.lcfn_library.LcfnBaseFragment;
import com.leibown.lcfn_library.SwipeStatusViewContainer;
import com.leibown.lcfn_library.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends LcfnBaseFragment {

    @Override
    public int getResId() {
        return R.layout.fragment_blank;
    }

    @Override
    public void ButterKnifeInit(View view) {

    }


    @Override
    public void unbindButterKnife() {

    }

    @Override
    protected void initViews() {
        setStatusBarBackgroundColor(Color.parseColor("#ee4c42"));
        getContentView().findViewById(R.id.tv_watingpay).setOnClickListener(this);
        ((SwipeStatusViewContainer) getStatusViewContainer()).setEnableLoadMore(true);
        ((SwipeStatusViewContainer) getStatusViewContainer()).setEnableRefresh(true);
        getStatusViewContainer().setOnEmptyClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("点击了空页面");
            }
        });

        getStatusViewContainer().setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("点击了错误页面");
            }
        });
        showContent();
    }

    @Override
    protected void loadData() {

    }


    private int count = 0;


    public void doClick() {
        count++;
        switch (count % 4) {
            case 0:
                showContent();
                break;
            case 1:
                showLoading();
                break;
            case 2:
                showEmpty();
                break;
            case 3:
                showRetry();
                break;
//            case 4:
//                otherStatusViewContainer.showOtherStatusView();
//                break;
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);


//        if (view.getId() == R.id.tv_watingpay) {
//            ShopCarCountDialog shopCarCountDialog = new ShopCarCountDialog(getContext(), 155, new ShopCarCountDialog.OnCountChangeListener() {
//                @Override
//                public void onChange(int count) {
//                    Log.e("leibown", "onChange_count:" + count);
//                }
//
//                @Override
//                public void onConfirm(int count) {
//                    Log.e("leibown", "onConfirm_count:" + count);
//                }
//            });
//            shopCarCountDialog.show();
//        }
    }
}
