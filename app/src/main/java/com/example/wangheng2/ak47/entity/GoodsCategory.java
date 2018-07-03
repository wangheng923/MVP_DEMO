package com.example.wangheng2.ak47.entity;

/**
 * @author wenyt
 * @created 2017年7月13日 下午1:36:53
 * @description 种类
 */
public class GoodsCategory {

	private String id;// 种类编号

	private String name;

	private String avatar;// 头像

	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
