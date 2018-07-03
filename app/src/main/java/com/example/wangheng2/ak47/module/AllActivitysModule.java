package com.example.wangheng2.ak47.module;

import com.example.wangheng2.ak47.ui.fragment.HomeFragment;
import com.example.wangheng2.ak47.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by wangheng2 on 2018/3/13.
 */

@Module(includes = {MainActivityModule.class, HomeFragmentModule.class, NetModule.class})
public abstract class AllActivitysModule {

    @Singleton
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivitytInjector();

    @Singleton
    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

}

//@Module(subcomponents = {
//        BaseActivityComponent.class, BaseFragementComponent.class
//},includes = {MainActivityModule.class, HomeFragmentModule.class, NetModule.class})
//public abstract class AllActivitysModule {
//
//    @Singleton
//    @ContributesAndroidInjector(modules = {})
//    abstract MainActivity contributeMainActivitytInjector();
//
//    @Singleton
//    @ContributesAndroidInjector(modules = {})
//    abstract HomeFragment contributeHomeFragment();
//
//}
