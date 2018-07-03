package com.example.wangheng2.ak47.module;

import com.example.wangheng2.ak47.ui.fragment.base.BaseFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by wangheng2 on 2018/3/13.
 */

@Subcomponent(modules = {
        AndroidSupportInjectionModule.class})
public interface BaseFragementComponent extends AndroidInjector<BaseFragment> {
    //每一个继承BaseActivity的Activity，都共享同一个SubComponent
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment> {
    }

}