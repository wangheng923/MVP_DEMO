package com.example.wangheng2.ak47.contract;

import com.example.wangheng2.ak47.entity.GoodsCategory;
import com.example.wangheng2.ak47.entity.StoreTransaction;
import com.example.wangheng2.ak47.entity.StoreTransactionSum;
import com.example.wangheng2.ak47.presenter.base.BasePresenter;
import com.example.wangheng2.ak47.presenter.base.BaseView;

import java.util.List;

/**
 * Created by wangheng2 on 2018/3/15.
 */

public class HomeContract {

    public interface View extends BaseView {
        void showGoodCategories(List<GoodsCategory> goodsCategories);

        void showLastTenTransactions(List<StoreTransaction> storeTransactions);

        void showStoreRanking(List<StoreTransactionSum> storeTransactionSums);

        boolean isWebviewInitialized();
    }

    public static abstract class Presenter<V> extends BasePresenter<V> {

        public abstract void listGoodCategories();

        public abstract void listTransactionsForLastTen();

        public abstract void listStoreRanking();
    }

}
