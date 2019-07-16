package com.leibown.lcfncommonlibrary;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.leibown.lcfn_library.LcfnBaseActivity;
import com.leibown.lcfn_library.swipe.SwipeRecyclerView;
import com.leibown.lcfn_library.utils.Utils;

public class Main2Activity extends LcfnBaseActivity {
    private SwipeRecyclerView swipeRecyclerView;
    private BlankFragment blankFragment;

//    private BlankFragment blankFragment;

    @Override
    public int getResId() {
        return R.layout.activity_main2;
    }

    @Override
    public void ButterKnifeInit() {

    }

    @Override
    protected void initViews() {
        Utils.init(this);
        setTitle("阿萨德科技东方红撒旦法哈萨克的积分hiuekjnaskdjhas看见好多看");
        hideStatusBar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        blankFragment = new BlankFragment();
        FragmentTransaction add = fragmentManager.beginTransaction().replace(R.id.fl_container, blankFragment);
        add.commitAllowingStateLoss();

//        swipeRecyclerView = findViewById(R.id.srv);
//        swipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add("" + i);
//        }
//        swipeRecyclerView.getRecyclerView().setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.fragment_blank, list) {
//
//            @Override
//            protected void convert(BaseViewHolder helper, String item) {
//
//            }
//        });
//        swipeRecyclerView.setOnLoadListener(new OnLoadListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRecyclerView.loadComplete();
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onRetry() {
//
//            }
//
//            @Override
//            public void onEmpty() {
//
//            }
//        });
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadComplete();
            }
        }, 2000);
    }

    @Override
    public void onEmpty() {
        super.onEmpty();
        ToastUtils.showShort("点击空页面");
    }

    @Override
    public void onReTry() {
        super.onReTry();
        ToastUtils.showShort("点击错误");
    }

    @Override
    public void Click_Back(View view) {
        blankFragment.doClick();
//        doClick();
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
    protected void loadData() {

    }

}
