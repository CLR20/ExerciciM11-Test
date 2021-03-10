package com.florist.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.*;

import com.florist.domain.*;

public class CreateFlorist {
	
	private static List <Trees> trees = new ArrayList<Trees> ();
	private static List <Flowers> flowers = new ArrayList<Flowers> ();
	private static List <Decorations> decorations = new ArrayList<Decorations> ();
	private HashMap <String, Double> ticket = new HashMap<String, Double>();
	
	public CreateFlorist () {	
		
		// Adding some trees.
		createTree("Pine", "1", 10, 6);
		createTree("Lemon tree", "1.5", 15, 9);
			
		// Adding some flowers.
		createFlower("Daisy", "white", 1, 20);
		createFlower("Rose", "red", 1.5, 30);			

		// Adding some decorations.
		createDecoration("Photo frame", "wood", 6, 12);
		createDecoration("Blue hanger", "plastic", 4, 40);
	}
	
	// Creating trees and adding them to the trees inventory.
	public void createTree(String name, String property, double price, int stock) {
		trees.add(new Trees (name, property, price, stock));		
	}
	
	public List <Trees> getTrees() {
		return trees;
	}
	
	// Creating flowers and adding them to the trees inventory.
	public void createFlower(String name, String property, double price, int stock) {
		flowers.add(new Flowers (name, property, price, stock));		
	}
		
	public List <Flowers> getFlowers() {
		return flowers;
	}
	
	// Creating decorations and adding them to the trees inventory.
	public void createDecoration(String name, String property, double price, int stock) {
		decorations.add(new Decorations (name, property, price, stock));		
	}
	
	public List <Decorations> getDecorations () {
		return decorations;
	}
	
	// Getting total stock values of all products.
	public double getTreesValue() {
		return trees.stream().collect(summingDouble(t -> t.getTreeValue()));
	}	
	public double getFlowersValue() {
		return flowers.stream().collect(summingDouble(t -> t.getFlowerValue()));
	}	
	public double getDecorationsValue() {
		return decorations.stream().collect(summingDouble(t -> t.getDecorationValue()));
	}	
	
	public String inventory() {
		return "\nOUR PRODUCTS:" + "\n\nTREES: " + trees + "\n\nFLOWERS: " + flowers 
				+ "\n\nDECORATIONS: " + decorations;
	}
	
	public String getInventoryValue () {
		double total = getTreesValue() + getFlowersValue() + getDecorationsValue();
		return "\nINVENTORY VALUES:" + "\n\nTREES: " + getTreesValue()
		+ "€\n\nFLOWERS: " + getFlowersValue() + "€\n\nDECORATIONS: " + getDecorationsValue()
		+ "€\n\nTOTAL INVENTORY VALUE: " + total + "€"; 
	}
	
	// Extracting trees, flowers and decorations, and adding sold items to ticket.
	public void extractTree (String name, int quantity, String sold) {
		for(Trees t: trees) {
			if (name.equals(t.getProductName())) {
				t.setProductStock(quantity);
				if (sold.equals("y")) {
					ticket.put(t.getProductName(), quantity * t.getProductPrice());
				}
			}
		}
	}	
	public void extractFlower (String name, int quantity, String sold) {
		for(Flowers f: flowers) {
			if (name.equals(f.getProductName())) {
				f.setProductStock(quantity);
				if (sold.equals("y")) {
					ticket.put(f.getProductName(), quantity * f.getProductPrice());
				}
			}
		}
	}	
	public void extractDecoration (String name, int quantity, String sold) {
		for(Decorations d: decorations) {
			if (name.equals(d.getProductName())) {
				d.setProductStock(quantity);
				if (sold.equals("y")) {
					ticket.put(d.getProductName(), quantity * d.getProductPrice());
				}
			}
		}
	}
	
	// Printing sales ticket.
	public String getTicket() {		
		return "\nAMOUNT BY PRODUCT: " + ticket + ", \n\nTOTAL SALES AMOUNT: " 
				+ ticket.entrySet().stream().collect(summingDouble(t -> t.getValue())) + "€";
	}	

}
