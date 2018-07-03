package com.example.wangheng2.ak47.module;

import com.example.wangheng2.ak47.ui.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangheng2 on 2018/3/13.
 */

@Module
public abstract class MainActivityModule {

    @Provides
    static String provideName() {
        return MainActivity.class.getName();
    }

//    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
//    abstract HomeFragment contributeAFragment();

}
