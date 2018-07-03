package com.example.wangheng2.ak47.presenter.base;


/**
 * Created by wangheng2 on 2018/3/15.
 */

public interface BaseView {
    void showNetWorkError(String msg);

    void showLoadingDialog();

    void shideLoadingDialog();
}
