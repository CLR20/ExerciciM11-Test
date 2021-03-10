package com.florist.domain;

public class Trees extends Products{		
	
	public Trees (String name, String property, double price, int stock) {
		super(name, property, price, stock);
	}	
	
	public String toString() {
		return "\nTree name: " + name + ", height: " + property
				+ " meters, Price: " + price + "€, Stock: " + stock + ", Stock value: " 
				+ getTreeValue() + "€\n";
	}
	
	public double getTreeValue() {
		return getProductValue();
	}

}
