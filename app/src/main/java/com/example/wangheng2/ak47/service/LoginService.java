package com.example.wangheng2.ak47.service;

import com.example.wangheng2.ak47.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by wangheng2 on 2018/3/13.
 */

public interface LoginService {

    @POST("/login")
    Call<User> login();
}
