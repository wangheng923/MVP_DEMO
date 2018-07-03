package com.example.wangheng2.ak47.service;

import com.example.wangheng2.ak47.entity.GoodsCategory;
import com.example.wangheng2.ak47.entity.ResultDto;
import com.example.wangheng2.ak47.entity.StoreTransaction;
import com.example.wangheng2.ak47.entity.StoreTransactionSum;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wangheng2 on 2018/3/15.
 */

public interface HomeService {

    @POST("/goodsCategory/list")
    Observable<ResultDto<List<GoodsCategory>>> listGoodCategories();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/store/searchStoreForLastTen")
    Observable<List<StoreTransaction>> listTransactionsForLastTen();

    @GET("/statistic/topStore/mobile")
    Observable<ResultDto<List<StoreTransactionSum>>> listStoreRanking(@Query("startDate") String startDate, @Query("endDate") String endDate, @Query("topNum") int topNum);
}
