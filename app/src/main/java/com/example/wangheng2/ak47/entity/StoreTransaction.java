package com.example.wangheng2.ak47.entity;

import java.util.List;

/**
 * @author ccc520
 * @createDate 2016年6月12日 上午9:39:01
 */
public class StoreTransaction {
    // 交易编号
    private String transactionId;
    // 客户名称
    private String customerName;
    // 身份证号
    private String idcard;
    // 电话
    private String telephone;
    // 物品类型Id
    private String item_typeId;
    // 物品类型Name
    private String item_typeName;
    // 物品颜色
    private String itemColor;
    // 物品品牌
    private String itemBrand;
    // 物品型号
    private String itemModel;
    // 串号
    private String iemi;
    // 物品重量
    private String itemWeight;
    // 物品材质
    private String itemMaterial;
    // 物品备注
    private String itemRemark;
    // 人证照
    private String handheld_idPic;
    // 身份证照
    private String idcardPic1;
    // 身份证照
    private String idcardPic2;
    // 物品照片列表

    private String item_picList;
    // 店铺ID

    private String storeId;
    // 店铺名称

    private String storeName;
    // 所属机构ID

    private String police_orgId;
    // 所属机构Name

    private String police_orgName;
    // 创建时间

    private String requestTime;
    // 交易类型 0:买入 1:卖出

    private String transactionType;
    // 开始日期
    private String beginDate;
    // 结束日期
    private String endDate;
    // 在Redis中的序号
    private String queueSeq;
    // 审核状态

    private String statusCode;
    // 审核状态备注

    private String statusRemark;
    // 审核人代码

    private String auditorId;
    // 价格

    private String price;
    // 审核人名称

    private String auditorName;
    // 更新时间，用于Solr的DIH
    private String updateTime;

    private String realIdCard;
    // 交易频率
    private String frequency;

    private List<TransactionAttach> transactionAttachList;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRealIdCard() {
        return realIdCard;
    }

    public void setRealIdCard(String realIdCard) {
        this.realIdCard = realIdCard;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPolice_orgId() {
        return police_orgId;
    }

    public void setPolice_orgId(String police_orgId) {
        this.police_orgId = police_orgId;
    }

    public String getPolice_orgName() {
        return police_orgName;
    }

    public void setPolice_orgName(String police_orgName) {
        this.police_orgName = police_orgName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getQueueSeq() {
        return queueSeq;
    }

    public void setQueueSeq(String queueSeq) {
        this.queueSeq = queueSeq;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getItem_typeId() {
        return item_typeId;
    }

    public void setItem_typeId(String item_typeId) {
        this.item_typeId = item_typeId;
    }

    public String getItem_typeName() {
        return item_typeName;
    }

    public void setItem_typeName(String item_typeName) {
        this.item_typeName = item_typeName;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemModel() {
        return itemModel;
    }

    public void setItemModel(String itemModel) {
        this.itemModel = itemModel;
    }

    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemMaterial() {
        return itemMaterial;
    }

    public void setItemMaterial(String itemMaterial) {
        this.itemMaterial = itemMaterial;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getHandheld_idPic() {
        return handheld_idPic;
    }

    public void setHandheld_idPic(String handheld_idPic) {
        this.handheld_idPic = handheld_idPic;
    }

    public String getIdcardPic1() {
        return idcardPic1;
    }

    public void setIdcardPic1(String idcardPic1) {
        this.idcardPic1 = idcardPic1;
    }

    public String getIdcardPic2() {
        return idcardPic2;
    }

    public void setIdcardPic2(String idcardPic2) {
        this.idcardPic2 = idcardPic2;
    }

    public String getItem_picList() {
        return item_picList;
    }

    public void setItem_picList(String item_picList) {
        this.item_picList = item_picList;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public List<TransactionAttach> getTransactionAttachList() {
        return transactionAttachList;
    }

    public void setTransactionAttachList(List<TransactionAttach> transactionAttachList) {
        this.transactionAttachList = transactionAttachList;
    }

    @Override
    public String toString() {
        return "StoreTransaction [transactionId=" + transactionId + ", customerName=" + customerName + ", idcard="
                + idcard + ", telephone=" + telephone + ", item_typeId=" + item_typeId + ", item_typeName="
                + item_typeName + ", itemColor=" + itemColor + ", itemBrand=" + itemBrand + ", itemModel=" + itemModel
                + ", iemi=" + iemi + ", itemWeight=" + itemWeight + ", itemMaterial=" + itemMaterial + ", itemRemark="
                + itemRemark + ", handheld_idPic=" + handheld_idPic + ", idcardPic1=" + idcardPic1 + ", idcardPic2="
                + idcardPic2 + ", item_picList=" + item_picList + ", storeId=" + storeId + ", storeName=" + storeName
                + ", police_orgId=" + police_orgId + ", police_orgName=" + police_orgName + ", requestTime="
                + requestTime + ", transactionType=" + transactionType + ", beginDate=" + beginDate + ", endDate="
                + endDate + ", queueSeq=" + queueSeq + ", statusCode=" + statusCode + ", statusRemark=" + statusRemark
                + ", auditorId=" + auditorId + ", price=" + price + ", auditorName=" + auditorName + ", updateTime="
                + updateTime + ", realIdCard=" + realIdCard + ", frequency=" + frequency + ", transactionAttachList="
                + transactionAttachList + "]";
    }

}
