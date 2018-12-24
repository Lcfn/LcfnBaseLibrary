package com.leibown.lcfncommonlibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.leibown.library.widget.status.StatusViewContainer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class OtherStatusViewContainer extends StatusViewContainer {

    private final ImageView imageView;


    public OtherStatusViewContainer(Context context) {
        super(context);
        imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.loading);
        addOtherStatusViews(imageView);
    }


    public void showOtherStatusView() {
        hideAllViews();
        imageView.setVisibility(View.VISIBLE);
    }

//    @Override
//    public ViewGroup setContainer() {
////        View v = View.inflate(mContext,R.layout.);
//
//        return (ViewGroup) v;
//    }

    @Override
    public void setContentView(View view) {
        if (contentView != null)
            container.removeView(contentView);
        SmartRefreshLayout smartRefreshLayout = new SmartRefreshLayout(mContext);
        smartRefreshLayout.addView(view);
        contentView = smartRefreshLayout;
        FrameLayout.LayoutParams childParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.addView(view, childParams);
    }
}
