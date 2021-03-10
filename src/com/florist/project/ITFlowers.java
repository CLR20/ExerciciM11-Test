package com.florist.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.florist.application.*;

public class ITFlowers extends Frame{	
	
	static MainFrame frame;
	static Scanner sc;		
	public static CreateFlorist ITFlowers = new CreateFlorist();
		
	public static void main (String [] args ) {

		ITFlowers itFlowers = new ITFlowers();
		frame = itFlowers.new MainFrame();	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}
	
	//Creating frame and panels.
	public class MainFrame extends JFrame {
		public MainFrame () {			
			setBounds(200, 200, 600, 600);
			setTitle("*** IT Flowers ***");
			Panel panel = new Panel();		
			add(panel);
		}
	}

	class Panel extends JPanel {
		
		JPanel adminButtons;
		JPanel stockAddButtons;
		JPanel stockExtractButtons;
		JTextArea screen;
		//JTextField answer;
		
		public Panel() {
			setLayout(new BorderLayout());
			ActionListener action = new PutAction();
			
			// Setting results screen.
			screen = new JTextArea("****** Welcome to IT FLOWERS ******\nSelect an option");
			screen.setSelectedTextColor(Color.BLACK);
			screen.setLineWrap(true);
			screen.setWrapStyleWord(true);
			add(screen, BorderLayout.CENTER);			
			
			// Setting admin buttons panel. 
			adminButtons = new JPanel();
			adminButtons.setLayout(new GridLayout(1, 4));
			addAdminButton("See inventory", action);
			addAdminButton("See stocks values", action);		
			addAdminButton("Get sales total", action);		
			addAdminButton("Leave app", action);
			add(adminButtons, BorderLayout.NORTH);
			
			// Setting products buttons panel. 
			stockAddButtons = new JPanel();
			stockAddButtons.setLayout(new GridLayout(3, 1));
			addAddButton("Add tree", action);		
			addAddButton("Add flower", action);		
			addAddButton("Add decoration", action);
			add(stockAddButtons, BorderLayout.WEST);
			stockExtractButtons = new JPanel();
			stockExtractButtons.setLayout(new GridLayout(3, 1));
			addExtractButton("Extract tree", action);
			addExtractButton("Extract flower", action);
			addExtractButton("Extract decoration", action);
			add(stockExtractButtons, BorderLayout.EAST);
		}
		
		// Creating methods to add buttons.
		private void addAdminButton (String title, ActionListener listen) {
			JButton button = new JButton(title);
			button.addActionListener(listen);
			if (title.equals("Leave app")) {
				add(button, BorderLayout.SOUTH);
			} else {
				adminButtons.add(button);
			}			
		}	
		private void addAddButton (String title, ActionListener listen) {
			JButton button = new JButton(title);
			button.addActionListener(listen);
			stockAddButtons.add(button);
		}	
		private void addExtractButton (String title, ActionListener listen) {
			JButton button = new JButton(title);
			button.addActionListener(listen);
			stockExtractButtons.add(button);
		}
		
		// Setting actions for the buttons.
		private class PutAction implements ActionListener {
			
			JLabel propertyLabel;
			JTextField nameText;
			JTextField propertyText;
			JTextField priceText;
			JTextField stockText;
			JTextField quantityText;
			JTextField soldText;
			String productType;
			JButton done;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String buttonTitle = e.getActionCommand();
								
				if (buttonTitle.equals("See inventory")) {
					screen.setText(ITFlowers.inventory());										
				} else if (buttonTitle.equals("Add tree")) {					
					AskProduct("tree");					
				} else if (buttonTitle.equals("Add flower")) {
					AskProduct("flower");
				} else if (buttonTitle.equals("Add decoration")) {
					AskProduct("decoration");
				} else if (buttonTitle.equals("Extract tree")) {
					ExtractProduct("tree");
				} else if (buttonTitle.equals("Extract flower")) {
					ExtractProduct("flower");
				} else if (buttonTitle.equals("Extract decoration")) {
					ExtractProduct("decoration");
				} else if (buttonTitle.equals("See stocks values")) {
					screen.setText(ITFlowers.getInventoryValue());
				} else if (buttonTitle.equals("Get sales total")) {
					screen.setText(ITFlowers.getTicket());
				} else if (buttonTitle.equals("Leave app")) {				
					System.exit(1);
				}					
			}				
			
			// Methods to ask product to add and clear screen afterwards.
			void AskProduct(String product) {
				productType = product;
				screen.setText("");
				screen.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel nameLabel = new JLabel("Add in the box the " + product + "'s name:");
				screen.add(nameLabel);
				nameText = new JTextField(20);					
				screen.add(nameText);	
				if (product.equals("tree")) {
					propertyLabel = new JLabel("Add in the the " + product + "'s height:");
				} else if (product.equals("flower")) {
					propertyLabel = new JLabel("Add in the the " + product + "'s color:");
				} else if (product.equals("decoration")) {
					propertyLabel = new JLabel("Add in the the " + product + "'s material:");
				}
				screen.add(propertyLabel);
				propertyText = new JTextField(20);					
				screen.add(propertyText);
				JLabel priceLabel = new JLabel("Add in the box the " + product + "'s price:");
				screen.add(priceLabel);
				priceText = new JTextField(20);					
				screen.add(priceText);
				JLabel stockLabel = new JLabel("Add in the box the " + product + "'s stock:");
				screen.add(stockLabel);
				stockText = new JTextField(20);					
				screen.add(stockText);
				done = new JButton("Done");
				CreatedDone isDone = new CreatedDone();
				done.addActionListener(isDone);
				screen.add(done);
				frame.setVisible(true);
			}			
			class CreatedDone implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					screen.removeAll();
					screen.revalidate();
					String name = nameText.getText().trim();					
					String property = propertyText.getText().trim();
					Double price = Double.parseDouble(priceText.getText().trim());
					int stock = Integer.parseInt(stockText.getText().trim());	
					if (productType.equals("tree")) {						
						screen.setText("\nNEW TREE: \nName: " + name + ", height: " + property 
								+ ", Price: " + price + ", Stock: " + stock);
						ITFlowers.createTree(name, property, price, stock);
					} else if (productType.equals("flower")) {
						screen.setText("\nNEW FLOWER: \nName: " + name + ", color: " + property 
								+ ", Price: " + price + ", Stock: " + stock);
						ITFlowers.createFlower(name, property, price, stock);
					} else if (productType.equals("decoration")) {
						screen.setText("\nNEW DECORATION: \nName: " + name + ", material: " + property 
								+ ", Price: " + price + ", Stock: " + stock);
						ITFlowers.createDecoration(name, property, price, stock);
					}					
				}
			}
			
			// Methods to ask product to extract and clear screen afterwards.
			void ExtractProduct(String product) {
				productType = product;
				screen.setText("");
				screen.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel nameLabel = new JLabel("Add in the box the " + product + "'s name:");
				screen.add(nameLabel);
				nameText = new JTextField(20);					
				screen.add(nameText);	
				JLabel quantityLabel = new JLabel("Add in the box the " + product + "'s quantity to extract:");
				screen.add(quantityLabel);
				quantityText = new JTextField(20);					
				screen.add(quantityText);
				JLabel soldLabel = new JLabel("The " + product + " has been sold? (y/n)");
				screen.add(soldLabel);
				soldText = new JTextField(20);					
				screen.add(soldText);
				done = new JButton("Done");
				ExtractedDone isDone = new ExtractedDone();
				done.addActionListener(isDone);
				screen.add(done);
				frame.setVisible(true);
			}
			class ExtractedDone implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					screen.removeAll();
					screen.revalidate();
					String name = nameText.getText().trim();					
					int quantity = Integer.parseInt(quantityText.getText().trim());
					String sold = soldText.getText().trim();
					if (productType.equals("tree")) {						
						screen.setText("\nTREE TO EXTRACT: \nName: " + name + ", quantity: " 
								+ quantity + ", Has been sold?: " + sold);
						ITFlowers.extractTree(name, quantity, sold);
					} else if (productType.equals("flower")) {
						screen.setText("\nFLOWER TO EXTRACT: \nName: " + name + ", quantity: " 
								+ quantity + ", Has been sold?: " + sold);
						ITFlowers.extractFlower(name, quantity, sold);
					} else if (productType.equals("decoration")) {
						screen.setText("\nDECORATION TO EXTRACT: \nName: " + name + ", quantity: " 
								+ quantity + ", Has been sold?: " + sold);
						ITFlowers.extractDecoration(name, quantity, sold);
					}					
				}
			}
		}	
	}

}