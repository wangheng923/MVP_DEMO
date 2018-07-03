package com.example.wangheng2.ak47;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;

import com.example.wangheng2.ak47.module.DaggerMyApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by wangheng2 on 2018/3/13.
 */

public class MyApplication extends MultiDexApplication implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerMyApplicationComponent.builder().build().inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
