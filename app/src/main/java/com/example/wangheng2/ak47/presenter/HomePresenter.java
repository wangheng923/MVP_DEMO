package com.example.wangheng2.ak47.presenter;

import android.text.format.DateFormat;
import android.util.Log;

import com.example.wangheng2.ak47.contract.HomeContract;
import com.example.wangheng2.ak47.entity.GoodsCategory;
import com.example.wangheng2.ak47.entity.ResultDto;
import com.example.wangheng2.ak47.entity.StoreTransaction;
import com.example.wangheng2.ak47.entity.StoreTransactionSum;
import com.example.wangheng2.ak47.service.HomeService;
import com.example.wangheng2.ak47.ui.fragment.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by wangheng2 on 2018/3/15.
 */

public class HomePresenter extends HomeContract.Presenter<HomeContract.View> {

    HomeService homeService;

    Retrofit retrofit;

    public HomePresenter(HomeContract.View view, HomeService homeService, Retrofit retrofit) {
        this.view = view;
        this.homeService = homeService;
        this.retrofit = retrofit;
    }

    @Override
    public void onInit() {
        listGoodCategories();
        listTransactionsForLastTen();
        listStoreRanking();
    }

    @Override
    protected void onDropView() {

    }

    @Override
    public void listGoodCategories() {
        Observable<ResultDto<List<GoodsCategory>>> listObservable = homeService.listGoodCategories();
        listObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResultDto<List<GoodsCategory>>>() {
            @Override
            public void accept(ResultDto<List<GoodsCategory>> resultDto) throws Exception {
                if ("success".compareTo(resultDto.getResult()) == 0) {
                    ((HomeContract.View) view).showGoodCategories(resultDto.getData());
                } else {
                    ((HomeContract.View) view).showNetWorkError(resultDto.getMessage());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ((HomeContract.View) view).showNetWorkError(throwable.getMessage());
            }
        });
    }

    @Override
    public void listTransactionsForLastTen() {
        Observable<List<StoreTransaction>> listObservable = homeService.listTransactionsForLastTen();
        listObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<StoreTransaction>>() {
            @Override
            public void accept(List<StoreTransaction> storeTransactions) throws Exception {
                ((HomeContract.View) view).showLastTenTransactions(storeTransactions);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ((HomeContract.View) view).showNetWorkError(throwable.getMessage());
            }
        });
    }

    @Override
    public void listStoreRanking() {
        boolean WebViewStatus = ((HomeContract.View) view).isWebviewInitialized();
        if (!WebViewStatus) return;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        String endDate = sdf.format(c.getTime());
        c.set(Calendar.DAY_OF_MONTH, 1);
        String startDate = sdf.format(c.getTime());
        Observable<ResultDto<List<StoreTransactionSum>>> listObservable = homeService.listStoreRanking(startDate, endDate, 5);
        listObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResultDto<List<StoreTransactionSum>>>() {
            @Override
            public void accept(ResultDto<List<StoreTransactionSum>> resultDto) throws Exception {
                if ("success".compareTo(resultDto.getResult()) == 0) {
                    ((HomeContract.View) view).showStoreRanking(resultDto.getData());
                } else {
                    ((HomeContract.View) view).showNetWorkError(resultDto.getMessage());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ((HomeContract.View) view).showNetWorkError(throwable.getMessage());
            }
        });
    }

}
