package com.example.wangheng2.ak47.entity;

/**
 * Description: 交易记录附加属性
 * 
 * @author wangheng2
 * @date 2017年12月12日
 */
public class TransactionAttach extends Attach {

	private String id;

	private String transactionId;

	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TransactionAttach [id=" + id + ", transactionId=" + transactionId + ", value=" + value + "]";
	}

}
