package com.example.wangheng2.ak47.module;

import com.example.wangheng2.ak47.contract.HomeContract;
import com.example.wangheng2.ak47.presenter.HomePresenter;
import com.example.wangheng2.ak47.service.HomeService;
import com.example.wangheng2.ak47.ui.fragment.HomeFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by wangheng2 on 2018/3/13.
 */


@Module
public class HomeFragmentModule {

    public static class HomeFragmentBuilder {
        public static HomeFragment homeFragment = new HomeFragment();

        public static HomeFragment build() {
            return homeFragment;
        }
    }

    @Provides
    HomeContract.View provideHomeContractView() {
        return  HomeFragmentBuilder.build();
    }

    @Provides
    HomeFragment provideHomeFragment() {
        return  HomeFragmentBuilder.build();
    }

    @Provides
    HomePresenter provideHomePresenter(HomeContract.View homeContractView, HomeService homeService, Retrofit retrofit) {
        return new HomePresenter(homeContractView, homeService, retrofit);
    }

}