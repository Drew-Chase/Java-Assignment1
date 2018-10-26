package tk.dccraft.Assignment_1.part_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tk.dccraft.init.Main;
import tk.dccraft.utils.BIOS;

/**
 * Assignment 1 Part 1... This class creates a Point of Sale Service
 * (POS-System) like program... Something I've been wanting to do since I saw
 * the god-awful POS-System at the Local House of Pizza. It's simple, but, if
 * this was for "actual use" I would do more.
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class MealTester extends Main {

	// Initializing Private Variables...
	// The Window Title
	public String title = "Bennigan's POS System";
	private List<String> menu_name = new ArrayList<String>();
	private List<Double> menu_price = new ArrayList<Double>();
	private List<String> yourOrder = new ArrayList<String>();
	private ArrayList<Double> yourOrderPrice = new ArrayList<Double>();
	private List<JButton> button = new ArrayList<JButton>();
	private JButton orderButton, clearButton, add, subtract, close;
	public JFrame orderWindow;
	private String moneyString;
	private double tax = 0.08, tip = 0.18;
	private JLabel tipLabel, titlelbl, stylelbl;
	private BufferedWriter bw;
	private Font font = getFont();
	private BIOS io = new BIOS();

	private int xOnFrame, yOnFrame;

	/**
	 * Initializes the MealTester.class
	 */
	public MealTester() {
		initApp();
		initEnt();
		initBev();
		initOrderWindow();
	}

	/**
	 * Initializes Appetizer
	 */
	public void initApp() {
		print("---------- Initializing Appetizers ----------");
		menu_name.add("a:Broccoli Bites");
		menu_price.add(5.39);
		print("Initializing Broccoli Bites");
		menu_name.add("a:Loaded Potato Skins");
		menu_price.add(6.49);
		print("Initializing Loaded Potato Skins");
		menu_name.add("a:Mozzarella Sticks");
		menu_price.add(5.29);
		print("Initializing Mozzarella Sticks");
	}

	/**
	 * Initializes Drinks
	 */
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

	/**
	 * Initializes Entries
	 */
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

	/**
	 * Initializes the Order Window
	 */
	private void initOrderWindow() {
		int width = 800, height = 600;
		// Initializing Content Pane
		Color bg = getBg(), fg = getFg();
		JPanel pane = new JPanel();
		// Initializing Frame
		orderWindow = new JFrame(title + " -- Menu");
		orderWindow.setSize(new Dimension(width, height));
		orderWindow.setBackground(bg);
		orderWindow.setUndecorated(true);
		orderWindow.addMouseListener(this);
		orderWindow.addMouseMotionListener(this);
		// orderWindow.setLayout(null);
		orderWindow.setVisible(true);
		orderWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		orderWindow.setLocationRelativeTo(null);
		orderWindow.setResizable(false);
		orderWindow.setContentPane(pane);
		pane.setBackground(bg);
		pane.setLayout(null);
		
		pane.addMouseListener(this);
		pane.addMouseMotionListener(this);

		// Initializing UI

		titlelbl = new JLabel(title);
		titlelbl.setForeground(getTitleFg());
		titlelbl.setLocation(25, -5);
		titlelbl.setFont(new Font(new Main().initFonts("ScorchedEarth.otf").getFontName(), Font.PLAIN, 28));
		titlelbl.setVisible(true);
		titlelbl.setSize(width, 100);
		titlelbl.setLayout(null);
		pane.add(titlelbl);

		Random r = new Random();

		stylelbl = new JLabel();
		stylelbl.setForeground(Color.WHITE);
		stylelbl.setFont(new Font(initFonts("BarcodeFont").getFontName(), Font.PLAIN, 72));
		stylelbl.setText(abc[r.nextInt(abc.length - 1)] + abc[r.nextInt(abc.length - 1)] + "-" + r.nextInt(50000) + "  " + abc[r.nextInt(abc.length - 1)] + abc[r.nextInt(abc.length - 1)] + "-" + r.nextInt(50000));
		stylelbl.setVisible(true);
		stylelbl.setSize(width, 80);
		stylelbl.setLayout(null);
		stylelbl.setLocation((width / 2) - 100, height - stylelbl.getHeight());
		pane.add(stylelbl);

		int x = 100, y = 100, xOffset = 0, yOffset = 0;
		for (int i = 0; i < menu_name.size(); i++) {
			JButton b = new JButton();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			moneyString = formatter.format(menu_price.get(i));
			b.setText(menu_name.get(i).substring(2) + " = " + moneyString);
			b.setEnabled(true);
			b.addActionListener(this);
			b.setSize(new Dimension(250, 25));
			b.setMaximumSize(new Dimension(400, 75));
			b.setVisible(true);
			b.setLocation(x + xOffset, y + yOffset);
			b.setFont(font);
			yOffset += 50;
			b.setLayout(null);
			if (i == 5) {
				yOffset = 0;
				xOffset += (orderWindow.getWidth() / 2);
			}
			b.setBackground(bg);
			b.setForeground(fg);
			b.setBorderPainted(false);
			button.add(b);
			pane.add(button.get(i));
		}

		orderButton = new JButton("Finish Order");
		orderButton.setEnabled(true);
		orderButton.addActionListener(this);
		orderButton.setSize(new Dimension(150, 25));
		orderButton.setForeground(fg);
		orderButton.setBackground(bg);
		orderButton.setVisible(true);
		orderButton.setLocation((orderWindow.getWidth() / 2) / 2, orderWindow.getHeight() - 170);
		orderButton.setLayout(null);
		orderButton.setBorderPainted(false);
		orderButton.setFont(font);
		orderButton.setMaximumSize(new Dimension(400, 75));
		pane.add(orderButton);

		tipLabel = new JLabel();
		tipLabel.setLocation((orderWindow.getWidth() / 2) - 50, orderButton.getLocation().y - 25);
		tipLabel.setEnabled(true);
		tipLabel.setToolTipText("This is the Tip Amount");
		tipLabel.setSize(38, 25);
		tipLabel.setText("18%");
		tipLabel.setForeground(fg);
		tipLabel.setFont(font);
		pane.add(tipLabel);

		subtract = new JButton("-");
		subtract.setSize(45, 25);
		subtract.setFont(font);
		subtract.addActionListener(this);
		subtract.setLocation(tipLabel.getLocation().x - 50, tipLabel.getLocation().y);
		subtract.setForeground(fg);
		subtract.setBackground(bg);
		subtract.setBorderPainted(false);
		pane.add(subtract);

		add = new JButton("+");
		add.setSize(45, 25);
		add.setFont(new Font("Arial", Font.BOLD, 19));
		add.addActionListener(this);
		add.setLocation(tipLabel.getLocation().x + 75, tipLabel.getLocation().y);
		add.setForeground(fg);
		add.setBackground(bg);
		add.setBorderPainted(false);
		add.setFont(font);
		pane.add(add);

		clearButton = new JButton("Clear Current Order");
		clearButton.setEnabled(true);
		clearButton.addActionListener(this);
		clearButton.setSize(new Dimension(150, 25));
		clearButton.setForeground(fg);
		clearButton.setBackground(bg);
		clearButton.setVisible(true);
		clearButton.setLocation((orderWindow.getWidth() / 2), orderButton.getLocation().y);
		clearButton.setLayout(null);
		clearButton.setBorderPainted(false);
		clearButton.setFont(font);
		clearButton.setMaximumSize(new Dimension(400, 75));
		pane.add(clearButton);

		close = new JButton("[X]");
		close.setEnabled(true);
		close.addActionListener(this);
		close.setSize(new Dimension(75, 50));
		close.setLocation(width - 75, 0);
		close.setForeground(getTitleFg());
		close.setBackground(bg);
		close.setOpaque(false);
		close.setFont(new Font("Arial", Font.BOLD, 28));
		close.setLayout(null);
		close.setBorderPainted(false);
		pane.add(close);

		updateFrame(orderWindow, orderWindow.getGraphics());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(close)) {
			orderWindow.dispose();
		}
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
					Random r = new Random();
					char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();
					stylelbl.setText(abc[r.nextInt(abc.length - 1)] + abc[r.nextInt(abc.length - 1)] + "-" + r.nextInt(50000) + "  " + abc[r.nextInt(abc.length - 1)] + abc[r.nextInt(abc.length - 1)] + "-" + r.nextInt(50000));
					stylelbl.setVisible(true);
					io.TextWriter("Recipt " + date + ".txt", fileContent, "Recipts/", false);
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

	@Override
	public void mousePressed(MouseEvent e) {
		xOnFrame = e.getX();
		yOnFrame = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = (int) e.getXOnScreen() - xOnFrame;
		int y = (int) e.getYOnScreen() - yOnFrame;
		orderWindow.setLocation(x, y);
	}

}
