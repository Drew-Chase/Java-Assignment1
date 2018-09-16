package tk.dccraft.Assignment_1.part_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import sun.net.ftp.FtpProtocolException;
import tk.dccraft.init.Main;

/**
 * Assignment 1 Part 1 ------------------------------ This class creates a Point
 * of Sale Service (POS-System) like program... Something I've been wanting to
 * do since I saw the god-awful POS-System at the Local House of Pizza.
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class MealTester extends Main implements ActionListener {

	// Initializing Private Variables... Which are all of them because ONE CLASS
	// BABY!
	// The Window Title
	public String title = "Bennigan's POS System";
	private List<String> menu_name = new ArrayList<String>();
	private List<Double> menu_price = new ArrayList<Double>();
	private List<String> yourOrder = new ArrayList<String>();
	private ArrayList<Double> yourOrderPrice = new ArrayList<Double>();
	private List<JButton> button = new ArrayList<JButton>();
	private JButton orderButton, clearButton, add, subtract;
	public JFrame orderWindow;
	private String moneyString;
	private double tax = 0.08, tip = 0.18;
	private JLabel tipLabel;

	private BufferedWriter bw;
	public PrintWriter writer;

	// the Constructor
	public MealTester() {
		initApp();
		initEnt();
		initBev();
		initOrderWindow();
	}

	// Initializes Appetizer
	public void initApp() {
		print("---------- Initializing Appetizers ----------");
		// brBites = new Food(5.39, "app", "Broccoli Bites");
		menu_name.add("a:Broccoli Bites");
		menu_price.add(5.39);
		print("Initializing Broccoli Bites");
		// loPS = new Food(6.49, "app", "Loaded Potato Skins");
		menu_name.add("a:Loaded Potato Skins");
		menu_price.add(6.49);
		print("Initializing Loaded Potato Skins");
		// mozzStick = new Food(5.29, "app", "Mozzarella Sticks");
		menu_name.add("a:Mozzarella Sticks");
		menu_price.add(5.29);
		print("Initializing Mozzarella Sticks");
	}

	// Initializes Drinks
	private void initBev() {
		print("--------- Initializing Beverages ------------");
		menu_name.add("d:Coca-Cola");
		menu_price.add(3.00);
		print("Initializing Coca-Cola");
		menu_name.add("d:Iced Tea");
		menu_price.add(3.00);
		print("Initializing Iced Tea");
		menu_name.add("d:Lemonade");
		menu_price.add(3.00);
		print("Initializing Lemonade");
		menu_name.add("d:Tap Water");
		menu_price.add(2.00);
		print("Initializing Tap Water");

	}

	// Initializes Entries
	private void initEnt() {
		print("--------- Initializing Entrees --------------");
		menu_name.add("e:Cajun Chicken & Pasta");
		menu_price.add(13.00);
		print("Initializing Cajun Chicken & Pasta");
		menu_name.add("e:Flat Iron Steak");
		menu_price.add(16.00);
		print("Initializing Flat Iron Steak");
		menu_name.add("e:Grilled Salmon Fillet");
		menu_price.add(15.00);
		print("Initializing Grilled Salmon Fillet");
		menu_name.add("e:Liver Dumplings");
		menu_price.add(13.00);
		print("Initializing Liver Dumplings");

	}

	// Initializes Windows
	// Initializes the Main Window
	private void initOrderWindow() {
		// Initializing Content Pane
		JPanel pane = new JPanel();
		// Initializing Frame
		orderWindow = new JFrame(title + " -- Menu");
		orderWindow.setSize(new Dimension(800, 600));
		orderWindow.setBackground(Color.DARK_GRAY);
		// orderWindow.setLayout(null);
		orderWindow.setVisible(true);
		orderWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		orderWindow.setLocationRelativeTo(null);
		orderWindow.setResizable(false);
		orderWindow.setContentPane(pane);
		pane.setBackground(Color.DARK_GRAY);
		pane.setLayout(null);

		// Initializing UI
		int x = 100, y = 100, xOffset = 0, yOffset = 0;
		for (int i = 0; i < menu_name.size(); i++) {
			JButton b = new JButton();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			moneyString = formatter.format(menu_price.get(i));
			b.setText(menu_name.get(i).substring(2) + " = " + moneyString);
			b.setEnabled(true);
			b.addActionListener(this);
			b.setSize(new Dimension(250, 25));
			b.setVisible(true);
			b.setLocation(x + xOffset, y + yOffset);
			yOffset += 50;
			b.setLayout(null);
			if (i == 5) {
				yOffset = 0;
				xOffset += (orderWindow.getWidth() / 2);
			}
			b.setBackground(Color.DARK_GRAY);
			b.setForeground(Color.WHITE);
			b.setBorderPainted(false);
			button.add(b);
			pane.add(button.get(i));
		}

		orderButton = new JButton("Finish Order");
		orderButton.setEnabled(true);
		orderButton.addActionListener(this);
		orderButton.setSize(new Dimension(150, 25));
		orderButton.setForeground(Color.WHITE);
		orderButton.setBackground(Color.DARK_GRAY);
		orderButton.setVisible(true);
		orderButton.setLocation((orderWindow.getWidth() / 2) / 2, orderWindow.getHeight() - 85);
		orderButton.setLayout(null);
		orderButton.setBorderPainted(false);
		pane.add(orderButton);

		tipLabel = new JLabel();
		tipLabel.setLocation((orderWindow.getWidth() / 2) - 50, orderButton.getLocation().y - 100);
		tipLabel.setEnabled(true);
		tipLabel.setToolTipText("This is the Tip Amount");
		tipLabel.setSize(38, 25);
		tipLabel.setText("18%");
		tipLabel.setForeground(Color.white);
		tipLabel.setFont(new Font("Arial", Font.BOLD, 18));
		pane.add(tipLabel);

		subtract = new JButton("-");
		subtract.setSize(45, 25);
		subtract.setFont(new Font("Arial", Font.BOLD, 19));
		subtract.addActionListener(this);
		subtract.setLocation(tipLabel.getLocation().x - 50, tipLabel.getLocation().y);
		subtract.setForeground(Color.white);
		subtract.setBackground(Color.DARK_GRAY);
		subtract.setBorderPainted(false);
		pane.add(subtract);

		add = new JButton("+");
		add.setSize(45, 25);
		add.setFont(new Font("Arial", Font.BOLD, 19));
		add.addActionListener(this);
		add.setLocation(tipLabel.getLocation().x + 75, tipLabel.getLocation().y);
		add.setForeground(Color.white);
		add.setBackground(Color.DARK_GRAY);
		add.setBorderPainted(false);
		pane.add(add);

		clearButton = new JButton("Clear Current Order");
		clearButton.setEnabled(true);
		clearButton.addActionListener(this);
		clearButton.setSize(new Dimension(150, 25));
		clearButton.setForeground(Color.WHITE);
		clearButton.setBackground(Color.DARK_GRAY);
		clearButton.setVisible(true);
		clearButton.setLocation((orderWindow.getWidth() / 2), orderWindow.getHeight() - 85);
		clearButton.setLayout(null);
		clearButton.setBorderPainted(false);
		pane.add(clearButton);

		updateFrame(orderWindow, orderWindow.getGraphics());

	}

	

	// This will provide an action event for the buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		// Adds and Event for incrementing a tip
		if (e.getSource().equals(add)) {
			NumberFormat format = NumberFormat.getPercentInstance();

			if (tip < 0.95) {
				tip += 0.05;

				tipLabel.setText(format.format(tip));
			} else {
				tip = 0.99;
			}
		}
		if (e.getSource().equals(subtract)) {
			NumberFormat format = NumberFormat.getPercentInstance();
			if (tip > 0.18) {
				tip -= 0.05;
				tipLabel.setText(format.format(tip));
			} else {
				tip = 0.18;
			}
		}

		for (int i = 0; i < menu_name.size(); i++) {
			if (e.getSource().equals(button.get(i))) {
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				moneyString = formatter.format(menu_price.get(i));
				yourOrder.add(menu_name.get(i));
				yourOrderPrice.add(menu_price.get(i));
				print("Added " + menu_name.get(i).substring(2) + " to Your Order for " + moneyString);
			}
		}
		String[] item = new String[yourOrder.size()];
		if (e.getSource().equals(orderButton)) {
			DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy-HH-mm");
			String date = dateFormatter.format(new Date());
			NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
			List<String> orderArray = new ArrayList<String>();
			double finalOrderPrice = 0.00;
			String finalOrder[] = new String[yourOrderPrice.size()];
			String money[] = new String[yourOrderPrice.size()];
			for (int i = 0; i < yourOrderPrice.size(); i++) {
				money[i] = moneyFormat.format(yourOrderPrice.get(i));
				item[i] = yourOrder.get(i);
				finalOrder[i] = item[i] + " = " + money[i];
				orderArray.add(finalOrder[i]);
			}
			if (finalOrder != null) {
				String finalOrderString = orderArray.toString().replace("[", "").replace("]", "").replace(",", "\n");
				for (double d : yourOrderPrice) {
					finalOrderPrice += d;
				}
				String subString = moneyFormat.format(finalOrderPrice);
				Path currentRelativePath = Paths.get("");
				String path = currentRelativePath.toAbsolutePath().toString();
				double totalCost = finalOrderPrice;
				totalCost += (finalOrderPrice * (tax + tip));
				String totalString = moneyFormat.format(totalCost);
				NumberFormat perFormat = NumberFormat.getPercentInstance();
				String fileContent = finalOrderString + "\n----------------------------\nSubTotal = " + subString + "\nFinal Total(incuding tip:" + perFormat.format(tip) + " and tax:" + perFormat.format(tax) + ") = " + totalString;
				try {
					reciptPrinter("Recipt " + date + ".txt", fileContent, "Recipts/");

				} catch (IOException e1) {
					print("An Issue happened because a programmer made a mistake\nrelatave to the ordering and printing/writing of the recipt.\nError: " + e1.getMessage());
				}
			}
		} else if (e.getSource().equals(clearButton)) {
			String finalOrder = yourOrder.toString().replace("[", "\"").replace("]", "\"");
			print(finalOrder + " Has been removed from your ticket");
			yourOrder.clear();
		}
	}

	public void reciptPrinter(String FileName, String fileContent, String FolderName) throws IOException {
		print("Accessing ReciptPrinter Method...");
		File f = new File(FolderName);
		try {
			if (f.mkdir()) {
				print("Directory Created in " + f.getAbsolutePath());
			} else {// Exists
				print("Directory is not created -- Maybe the File already exists");
			}
		} catch (Exception e) {
			print(e.getMessage());
		}
		try {
			bw = new BufferedWriter(new FileWriter(FolderName + FileName, true));
			bw.write(fileContent);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			print("Had an issue with Appending file " + FileName + " in ReciptPrinter Meathod.  ERROR: " + e.getMessage());
		} finally {
			if (bw != null) {
				bw.close();
			}
		}

//		ftpSend("ftp://192.168.1.31", FileName, fileContent);

	}

	public void ftpSend(String url, String name, String content) {
		FileOutputStream fos = null;
		FTPClient client = new FTPClient();
		try {
			client.connect(url);
			File f = new File(name);
			bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(content);
			bw.newLine();
			bw.flush();
			client.setFileType(FTP.BINARY_FILE_TYPE);
			try (InputStream input = new FileInputStream(f)) {
				client.storeFile(url + name, input);
			}
			fos = new FileOutputStream(f);
			
			client.logout();
			print("Sending " + content + "\n to " + url);
		} catch (IOException e) {
			print("ERROR with the ftpSender Method\n" + e.getMessage());
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					print("Error with BufferedWriter closing\nERROR:" + e.getMessage());
				}
			}
			try {
				if (fos != null) {
					fos.close();
				}
				client.disconnect();
			} catch (IOException e) {
				print("ERROR with the ftpSender Method\n" + e.getMessage());
			}
		}
	}

}
