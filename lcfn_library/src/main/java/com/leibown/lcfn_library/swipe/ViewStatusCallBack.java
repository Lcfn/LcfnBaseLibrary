package com.leibown.lcfn_library.swipe;

/**
 * Created by leibown on 2018/7/12.
 */

public interface ViewStatusCallBack {

    /**
     * 显示Loading状态
     */
    void showLoading();

    /**
     * 显示Empty状态
     */
    void showEmpty();

    /**
     * 显示Retry状态
     */
    void showRetry();

    /**
     * 显示内容
     */
    void showContent();


    /**
     * 加载完成
     */
    void loadComplete();


    /**
     * 没有更多数据
     */
    void setNoMoreData();

}
