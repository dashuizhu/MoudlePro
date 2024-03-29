package com.test.basemoudle;

/**
 * @author zhuj 2019-08-28 20:36.
 */
public interface IBaseView {

    /**
     * 显示进度条
     */
    void showProgress();

    /**
     * 隐藏进度条
     */
    void hideProgress();

    /**
     * 显示网络异常
     */
    void onError(Throwable e);

    /**
     * 网络recyclerView加载错误， 针对做空界面、错误界面等
     * @param e
     */
    void onLoadError(Throwable e);

    /**
     * 成功
     * @param successObj 成功返回的对象
     */
    void onSuccess(Object successObj);

}
