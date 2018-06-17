package com.pojo;

public class Shopping {
	
	private int userId;
	private String goodName;
	private double goodPrice;
	private int shoppingId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public double getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}
	public int getShoppingId() {
		return shoppingId;
	}
	public void setShoppingId(int shoppingId) {
		this.shoppingId = shoppingId;
	}
	@Override
	public String toString() {
		return "Shopping [userId=" + userId + ", goodName=" + goodName + ", goodPrice=" + goodPrice + ", shoppingId="
				+ shoppingId + "]";
	}
}
