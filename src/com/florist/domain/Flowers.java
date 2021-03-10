package com.florist.domain;

public class Flowers extends Products{
	
	public Flowers (String name, String property, double price, int stock) {
		super(name, property, price, stock);
	}	
	
	public String toString() {
		return "\nFlower name: " + name + ", color: " + property
				+ ", Price: " + price + "€, Stock: " + stock + ", Stock value: " 
				+ getFlowerValue() + "€\n";
	}	

	public double getFlowerValue() {
		return getProductValue();
	}
	

}
