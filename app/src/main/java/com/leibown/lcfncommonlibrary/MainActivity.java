package com.leibown.lcfncommonlibrary;

import android.view.View;

import com.leibown.lcfn_library.LcfnBaseActivity;
import com.leibown.lcfn_library.swipe.SwipeRecyclerView;
import com.leibown.library.widget.status.StatusViewContainer;

public class MainActivity extends LcfnBaseActivity {


    private OtherStatusViewContainer otherStatusViewContainer;

    @Override
    public void ButterKnifeInit() {

    }

    @Override
    protected void initViews() {
        SwipeRecyclerView swipeRecyclerView  = findViewById(R.id.recyclerview);
//        ImageView iv = findViewById(R.id.iv);
//        ShowImageUtils.showImageView(this, "https://ws4.sinaimg.cn/large/006tKfTcgy1ftbv3p3x6tj308c08c74a.jpg", iv);

    }

    @Override
    protected StatusViewContainer initStatusViewContainer() {
        otherStatusViewContainer = new OtherStatusViewContainer(this);
        return otherStatusViewContainer;
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
                otherStatusViewContainer.showOtherStatusView();
                break;
        }
    }

}
