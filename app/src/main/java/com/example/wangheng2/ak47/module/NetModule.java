package com.example.wangheng2.ak47.module;

import com.example.wangheng2.ak47.okhttp.digest.Credentials;
import com.example.wangheng2.ak47.service.HomeService;
import com.example.wangheng2.ak47.util.HttpConstants;
import com.example.wangheng2.ak47.util.HttpUtil;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangheng2 on 2018/3/13.
 */

@Module
public class NetModule {

    @Provides
    Retrofit proviceRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Credentials credentials = new Credentials("admin", "admin");
        HttpUtil.setCredentials(credentials);
        builder.baseUrl(HttpConstants.ROOT_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(HttpUtil.getClient());
        return builder.build();
    }

    @Provides
    HomeService provideHomeService(Retrofit retrofit) {
        return retrofit.create(HomeService.class);
    }
}
