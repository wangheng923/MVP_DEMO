package com.example.wangheng2.ak47.entity;

import java.util.ArrayList;

public class StoreTransactionSum {
    String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    ArrayList<TransactionStatisticCount> itemList;

    public ArrayList<TransactionStatisticCount> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<TransactionStatisticCount> itemList) {
        this.itemList = itemList;
    }

}
