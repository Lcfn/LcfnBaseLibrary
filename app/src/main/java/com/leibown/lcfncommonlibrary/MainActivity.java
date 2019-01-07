package com.leibown.lcfncommonlibrary;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.leibown.lcfn_library.LcfnBaseActivity;
import com.leibown.lcfn_library.swipe.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends LcfnBaseActivity {


    @Override
    public void ButterKnifeInit() {

    }

    @Override
    protected void initViews() {
        SwipeRecyclerView swipeRecyclerView = findViewById(R.id.recyclerview);
        swipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(i + "");
        }
        swipeRecyclerView.getRecyclerView().setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_actionbar, strings) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_title, item);
            }
        });
//        ImageView iv = findViewById(R.id.iv);
//        ShowImageUtils.showImageView(this, "https://ws4.sinaimg.cn/large/006tKfTcgy1ftbv3p3x6tj308c08c74a.jpg", iv);

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
            case 4:
                break;
        }
    }

}
