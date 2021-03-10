package com.florist.domain;

public abstract class Products {
	
	protected String name;
	protected String property;
	protected double price;
	protected int stock;
	protected double productValue;
	
	public Products (String name, String property, double price, int stock) {
		this.name = name;
		this.property = property;
		this.price = price;
		this.stock = stock;
		productValue = price * stock;
	}
	
	public String getProductName() {
		return name;
	}
	
	public String getProductProperty() {
		return property;
	}
	
	public double getProductPrice() {
		return price;
	}
	
	public double getProductStock() {
		return stock;
	}
	
	public double getProductValue() {
		return productValue;
	}
	
	public void setProductStock(int quantity) {
		stock -= quantity;
		productValue = price * stock;
	}
	
	public abstract String toString();

}
