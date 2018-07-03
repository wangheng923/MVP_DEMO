package com.example.wangheng2.ak47.module;

import com.example.wangheng2.ak47.MyApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by wangheng2 on 2018/3/13.
 */

@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class, AllActivitysModule.class})
public interface MyApplicationComponent {
    void inject(MyApplication application);
}
