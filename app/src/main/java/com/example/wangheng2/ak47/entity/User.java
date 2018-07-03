package com.example.wangheng2.ak47.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class User{
	public interface UserView{};
	private String account;
	private String name;
	private String cardNumber;
	private String bestpayId;
	private String unitCode;
	private String phoneNumber;
	private String creator;
	private int cardType;
	private int status;
	private int loginfailed;
	private String password;
	private Timestamp creatTime;
	private boolean fixed;
	private boolean locked;
	private List<Role> roles=new ArrayList<Role>();
	private List<String> privileges=new ArrayList<String>();

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBestpayId() {
		return bestpayId;
	}

	public void setBestpayId(String bestpayId) {
		this.bestpayId = bestpayId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLoginfailed() {
		return loginfailed;
	}

	public void setLoginfailed(int loginfailed) {
		this.loginfailed = loginfailed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
}
