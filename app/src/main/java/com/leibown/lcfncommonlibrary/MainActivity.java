package com.leibown.lcfncommonlibrary;

import android.widget.ImageView;

import com.leibown.lcfn_library.LcfnBaseActivity;
import com.leibown.lcfn_library.glide.ShowImageUtils;

public class MainActivity extends LcfnBaseActivity {


    @Override
    public void ButterKnifeInit() {

    }

    @Override
    protected void initViews() {
        ImageView iv = findViewById(R.id.iv);
        ShowImageUtils.showImageView(this, "https://ws4.sinaimg.cn/large/006tKfTcgy1ftbv3p3x6tj308c08c74a.jpg", iv);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getResId() {
        return R.layout.activity_main;
    }
}
