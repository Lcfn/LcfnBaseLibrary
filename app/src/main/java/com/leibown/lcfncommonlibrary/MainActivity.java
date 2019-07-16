package com.leibown.lcfncommonlibrary;


import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leibown.lcfn_library.LcfnBaseActivity;
import com.leibown.lcfn_library.swipe.OnLoadListener;
import com.leibown.lcfn_library.swipe.SwipeRecyclerView;
import com.leibown.lcfn_library.utils.ToastUtils;
import com.leibown.lcfn_library.utils.Utils;
import com.leibown.lcfn_library.widget.ShopCarCountDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends LcfnBaseActivity {


    private SwipeRecyclerView swipeRecyclerView;

    @Override
    public void ButterKnifeInit() {

    }

    @Override
    protected void initViews() {
        setTitle("111111111111111111111111111111111111111");
        Utils.init(this);
        swipeRecyclerView = findViewById(R.id.recyclerview);
        swipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(i + "");
        }
        BaseQuickAdapter<String, BaseViewHolder> quickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_actionbar, strings) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_title, item);
            }
        };

        swipeRecyclerView.getRecyclerView().setAdapter(quickAdapter);
        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopCarCountDialog shopCarCountDialog=new ShopCarCountDialog(MainActivity.this, 1, 200, new ShopCarCountDialog.OnCountChangeListener() {
                    @Override
                    public void onChange(int count) {
                        Log.e("onChange",count+"");

                    }

                    @Override
                    public void onConfirm(int count) {
                        Log.e("onConfirm",count+"");
                    }
                });
                shopCarCountDialog.show();
            }
        });


//        ImageView iv = findViewById(R.id.iv);
//        ShowImageUtils.showImageView(this, "https://ws4.sinaimg.cn/large/006tKfTcgy1ftbv3p3x6tj308c08c74a.jpg", iv);
        swipeRecyclerView.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRetry() {
                ToastUtils.show("你点击了错误");
            }

            @Override
            public void onEmpty() {
                ToastUtils.show("你点击了空");
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getResId() {
        return R.layout.activity_main;
    }

    @Override
    public void Click_Back(View view) {
        doClick();
    }


    private int count = 0;


    public void doClick() {
        count++;
        switch (count % 5) {
            case 0:
                swipeRecyclerView.showContent();
                break;
            case 1:
                swipeRecyclerView.showLoading();
                break;
            case 2:
                swipeRecyclerView.showEmpty();
                break;
            case 3:
                swipeRecyclerView.showRetry();
                break;
            case 4:
                break;
        }
    }

}
