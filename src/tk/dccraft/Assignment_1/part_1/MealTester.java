package tk.dccraft.Assignment_1.part_1;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MealTester {

	private String title = "Bennigan's POS System";
	// private double price;
	// private Food brBites, loPS, mozzStick, cCaP, steak, salmon, liver, cola,
	// iceTea, lemonade, water;

	public static HashMap<String, Double> menu = new HashMap<String, Double>();
	public JFrame consoleWindow, orderWindow;
	public JTextArea console;

	public static void main(String[] args) {
		new MealTester();
	}

	public MealTester() {
		initConsoleWindow();
		initApp();
		initEnt();
		initBev();
		initOrderWindow();

	}

	public void initApp() {
		print("---------- Initializing Appetizers ----------");
		// brBites = new Food(5.39, "app", "Broccoli Bites");
		menu.put("Broccoli Bites", 5.39);
		print("Initializing Broccoli Bites");
		// loPS = new Food(6.49, "app", "Loaded Potato Skins");
		menu.put("Loaded Potato Skins", 6.49);
		print("Initializing Loaded Potato Skins");
		// mozzStick = new Food(5.29, "app", "Mozzarella Sticks");
		menu.put("Mozzarella Sticks", 5.29);
		print("Initializing Mozzarella Sticks");
	}

	private void initBev() {
		print("--------- Initializing Beverages ------------");
		menu.put("Coca-Cola", 3.00);
		print("Initializing Coca-Cola");
		menu.put("Iced Tea", 3.00);
		print("Initializing Iced Tea");
		menu.put("Lemonade", 3.00);
		print("Initializing Lemonade");
		menu.put("Tap Water", 2.00);
		print("Initializing Tap Water");

	}

	private void initEnt() {
		print("--------- Initializing Entrees --------------");

	}

	// Initializes Windows
	private void initOrderWindow() {
		// Initializing Frame
		orderWindow = new JFrame(title + " -- Menu");
		orderWindow.setSize(new Dimension(800, 600));
		orderWindow.setVisible(true);
		orderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		orderWindow.setLocationRelativeTo(null);

		// Initializing UI
		JButton button[] = new JButton[menu.size()];
		print(menu.size() + " menu string length");
		

	}

	public void initConsoleWindow() {
		// Initializing Frame
		consoleWindow = new JFrame(title + " -- Console");
		consoleWindow.setSize(new Dimension(800 / 2, 600 / 2));
		consoleWindow.setVisible(true);
		consoleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// consoleWindow.setResizable(false);

		// Initializing UI
		console = new JTextArea();
		console.setEditable(false);
		console.setSize(new Dimension(consoleWindow.getWidth() - 50, consoleWindow.getHeight() - 50));
		console.setBackground(Color.BLACK);
		console.setForeground(Color.WHITE);
		consoleWindow.add(console);
	}

	public void print(String message) {
		System.out.println(message);
		console.append(message + "\n");
	}

}
