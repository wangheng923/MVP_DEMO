package com.example.wangheng2.ak47.entity;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wangheng2 on 2018/1/23.
 */

public class Transaction {

    private String title;
    private String time;
    private String transactionType;
    private String goodType;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
