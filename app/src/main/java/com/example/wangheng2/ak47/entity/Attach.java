package com.example.wangheng2.ak47.entity;

/**
 * Description: 附加属性表
 *
 * @author wangheng2
 * @date 2017年12月12日
 */
public class Attach {

    protected String attachId;

    protected String displayName;

    private String keyName;

    private String categoryId;

    private String required;

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    @Override
    public String toString() {
        return "Attach [attachId=" + attachId + ", displayName=" + displayName + ", keyName=" + keyName
                + ", categoryId=" + categoryId + ", required=" + required + "]";
    }

}
