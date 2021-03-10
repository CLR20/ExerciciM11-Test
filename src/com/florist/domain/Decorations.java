package com.florist.domain;

public class Decorations extends Products{
	
	public Decorations (String name, String property, double price, int stock) {
		super(name, property, price, stock);
	}	
	
	public String toString() {
		return "\nDecoration name: " + name + ", material: " + property
				+ ", Price: " + price + "€, Stock: " + stock + ", Stock value: " 
				+ getDecorationValue() + "€\n";
	}	

	public double getDecorationValue() {
		return getProductValue();
	}
	

}
