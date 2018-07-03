package com.example.wangheng2.ak47.presenter.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


/**
 * Created by wangheng2 on 2018/3/15.
 */

public abstract class BasePresenter<V> extends Presenter {

    @Override
    protected void onCreate(@Nullable Bundle savedState) {

    }

    @Override
    public void onResume() {
        this.onInit();
    }

    @Override
    public void onDestroy() {
        super.dropView();
    }

    @Override
    protected void onSave(Bundle state) {

    }

    public abstract void onInit();
}
