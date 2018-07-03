package com.example.wangheng2.ak47.entity;

/*
 * 按照分局特定时间内统计的各类型的交易数量
 * */
public class TransactionStatisticCount {
    // 统计数量
    int statisticCount;

    // 类型名称
    String itemTypeName;

    public int getStatisticCount() {
        return statisticCount;
    }

    public void setStatisticCount(int statisticCount) {
        this.statisticCount = statisticCount;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    // 类型Id
    String itemTypeId;

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    String lng;

    String lat;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    String orgId;

    String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


}
