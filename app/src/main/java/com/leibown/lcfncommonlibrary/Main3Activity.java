package com.leibown.lcfncommonlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        SmartRefreshLayout outSrl = findViewById(R.id.out_srl);
//        outSrl.setEnableRefresh(false);
//        outSrl.setEnableLoadMore(false);
//        outSrl.setEnableOverScrollDrag(false);
        outSrl.setEnabled(false);

        SmartRefreshLayout smartRefreshLayout = findViewById(R.id.srl);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            strings.add(i + "");
        }
        recyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_actionbar, strings) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_title, item);
            }
        });

    }
}
