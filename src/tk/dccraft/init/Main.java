package tk.dccraft.init;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import tk.dccraft.Assignment_1.part_1.MealTester;
import tk.dccraft.Assignment_1.part_2.CalendarTester;
import tk.dccraft.Assignment_2.bank.SavingsAccountTester;
import tk.dccraft.http.updater.Updater;
import tk.dccraft.lab1.Lab1;
import tk.dccraft.lab2.VendingMachine;
import tk.dccraft.utils.BIOS;
import tk.dccraft.utils.settings.PreferenceWindow;

/**
 * The Core class for the program... Contains the Main Method Creates the
 * Console Window
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Main implements ActionListener {

	public static JTextArea console;
	private static JFrame consoleWindow;
	private static DefaultCaret caret;
	private static JMenuBar menuBar;
	private static JMenu file, edit, assign, assign_1, assign_2, labs;
	private static JMenuItem pos, dob, exit, sat, update, preference, load, lab_1, lab_2;
	private static Color bg, fg, cbg, cfg;
	private static int fontSize = 12, scd = 50;
	private static boolean isDefaultConsole;

	private static BIOS io = new BIOS();
	private static int index = 0;
	public static Font font;

	/**
	 * Initializes the Custom Console Window
	 */
	public static void initConsoleWindow() {
		// Initializing Frame

		consoleWindow = new JFrame("Console");
		consoleWindow.setSize(new Dimension(500, 300));
		consoleWindow.setUndecorated(true);
		consoleWindow.setLocation(50, 100);
		consoleWindow.setResizable(true);
		consoleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		consoleWindow.setBackground(getConsoleBg());
		consoleWindow.setForeground(getConsoleFg());

		// Initializing UI
		// Menu Items
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(getConsoleFg());
		menuBar.setBackground(getConsoleBg());

		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.setBackground(getConsoleBg());
		file.setForeground(getConsoleFg());
		menuBar.add(file);

		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		edit.addActionListener(new Main());
		edit.setForeground(getConsoleFg());
		edit.setBackground(getConsoleBg());
		edit.setBorderPainted(false);
		menuBar.add(edit);

		exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_X);
		exit.setForeground(getConsoleFg());
		exit.setBackground(getConsoleBg());
		exit.addActionListener(new Main());
		exit.setSize(new Dimension(20, 20));
		menuBar.add(exit);

		assign = new JMenu("Assignments");
		assign.setForeground(getConsoleFg());
		assign.setBackground(getConsoleBg());
		file.add(assign);

		labs = new JMenu("Labs");
		labs.setForeground(getConsoleFg());
		labs.setBackground(getConsoleBg());
		file.add(labs);

		// Labs Menu
		lab_1 = new JMenuItem("Lab 1");
		lab_1.addActionListener(new Main());
		lab_1.setBackground(getConsoleBg());
		lab_1.setForeground(getConsoleFg());
		labs.add(lab_1);

		lab_2 = new JMenuItem("Lab 2");
		lab_2.addActionListener(new Main());
		lab_2.setBackground(getConsoleBg());
		lab_2.setForeground(getConsoleFg());
		labs.add(lab_2);
		// Assignment 1 Menu
		assign_1 = new JMenu("Assignment 1");
		assign_1.setForeground(getConsoleFg());
		assign_1.setBackground(getConsoleBg());
		assign.add(assign_1);

		dob = new JMenuItem("CalendarTester");
		dob.addActionListener(new Main());
		dob.setBackground(getConsoleBg());
		dob.setForeground(getConsoleFg());
		assign_1.add(dob);

		pos = new JMenuItem("MealTester");
		pos.addActionListener(new Main());
		pos.setBackground(getConsoleBg());
		pos.setForeground(getConsoleFg());
		assign_1.add(pos);

		// Assignment 2 Menu
		assign_2 = new JMenu("Assignment 2");
		assign_2.setBackground(getConsoleBg());
		assign_2.setForeground(getConsoleFg());
		assign.add(assign_2);

		sat = new JMenuItem("Savings Account Tester");
		sat.addActionListener(new Main());
		sat.setBackground(getConsoleBg());
		sat.setForeground(getConsoleFg());
		assign_2.add(sat);

		update = new JMenuItem("Update");
		update.addActionListener(new Main());
		update.setBackground(getConsoleBg());
		update.setForeground(getConsoleFg());
		file.add(update);

		// Working on Edit Menu
		preference = new JMenuItem("Preferences");
		preference.addActionListener(new Main());
		preference.setForeground(getConsoleFg());
		preference.setBackground(getConsoleBg());
		edit.add(preference);

		load = new JMenuItem("Load Default Files");
		load.addActionListener(new Main());
		load.setBackground(getConsoleBg());
		load.setForeground(getConsoleFg());
		edit.add(load);

		// Console
		console = new JTextArea("");
		console.setSize(new Dimension(consoleWindow.getWidth(), consoleWindow.getHeight()));
		console.setBackground(getConsoleBg());
		console.setForeground(getConsoleFg());
		console.setEditable(false);

		GridBagLayout gbl = new GridBagLayout();
		consoleWindow.setLayout(gbl);

		JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		caret = (DefaultCaret) console.getCaret();
		caret.setUpdatePolicy(2);
		scroll.getVerticalScrollBar().setBackground(getConsoleBg());
		scroll.getVerticalScrollBar().setForeground(getTitleFg(getConsoleBg()));
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.insets = new Insets(0, 0, 0, 0);
		scrollConstraints.fill = 1;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.gridwidth = 3;
		scrollConstraints.gridheight = 2;
		scrollConstraints.weightx = 1.0;
		scrollConstraints.weighty = 1.0;
		consoleWindow.add(scroll, scrollConstraints);
		consoleWindow.setJMenuBar(menuBar);
		console.setBackground(getConsoleBg());
		console.setForeground(getConsoleFg());
		
	}

	/**
	 * Prints the message to either console
	 * 
	 * @param message
	 */
	public void print(Object message) {
		if (isDefaultConsole)
			console.append(message.toString() + "\n");
		else
			System.out.println(message + "\n");

	}

	/**
	 * Prints the message to either console and makes a new line base on the
	 * newLine param
	 * 
	 * @param message
	 * @param newLine
	 */
	public void print(Object message, int newLine) {
		if (isDefaultConsole) {
			if (newLine == 1)
				console.append(message.toString() + "\n");
			else if (newLine == 0)
				console.append(message.toString());
			else if (newLine > 1) {
				console.append(message.toString());
				for (int i = 0; i < newLine; i++)
					console.append("\n");
			}
		} else {
			if (newLine == 1)
				System.out.println(message);
			else if (newLine == 0)
				System.out.print(message);
			else if (newLine > 1) {
				System.out.print(message);
				for (int i = 0; i < newLine; i++) {
					System.out.println("\n");
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			io.TextReader("Colors.ini", "Settings/", "style");
			setBg(new Color(Integer.decode(io.bg)));
			setFg(new Color(Integer.decode(io.fg)));
			setFontSize(Integer.parseInt(io.ft));
			setIndex(Integer.parseInt(io.index));
		} catch (Exception e) {
			new Main().print("Files not found... Creating them");
			loadDefaultFiles();
		}
		if (System.console() == null) {
			isDefaultConsole = true;
			setConsoleBg(new Color(Integer.decode(io.cbg)));
			setConsoleFg(new Color(Integer.decode(io.cfg)));
			initConsoleWindow();
			new Main().print("(c) A Drew Chase Project", 2);
			String message = "IF THIS IS THE FIRST LAUNCH PLEASE RUN:\n----" + edit.getText() + "\n----------" + load.getText() + "\nTO GENERATE THE NECESSARY SETTINGS";
			new Main().print(message);
			for (int i = 0; i < message.length(); i++) {
				new Main().print("-", 0);
			}
			new Main().print("", 3);
			EventQueue.invokeLater(() -> {
				consoleWindow.setVisible(true);
			});
		} else {
			isDefaultConsole = false;
			// Everything Below is for standard Command Line Launch
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("pos")) {
					new MealTester();
				} else if (args[0].equalsIgnoreCase("dob")) {
					new CalendarTester();
				} else if (args[0].equalsIgnoreCase("bank")) {
					new SavingsAccountTester();
				} else if (args[0].equalsIgnoreCase("lab1")) {
					new Lab1();
				} else if (args[0].equalsIgnoreCase("lab2")) {
					Lab2();
				} else if (args[0].equalsIgnoreCase("load")) {
					loadDefaultFiles();
					System.exit(0);
				} else if (args[0].equalsIgnoreCase("pref")) {
					new PreferenceWindow();
				} else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
					help();
				} else {
					new Main().print(args[0] + " is not a proper argument.");
					help();
				}
			} else {
				while (true) {
					Scanner sc = new Scanner(System.in);
					new Main().print("type help or ? for a list of commands and cmd arguments");
					String input = sc.nextLine();
					if (input.equalsIgnoreCase("pos")) {
						new MealTester();
					} else if (input.equalsIgnoreCase("dob")) {
						new CalendarTester();
					} else if (input.equalsIgnoreCase("lab1")) {
						new Lab1();
					} else if (input.equalsIgnoreCase("lab2")) {
						Lab2();
					} else if (input.equalsIgnoreCase("bank")) {
						new SavingsAccountTester();
					} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
						System.exit(0);
					} else if (input.equalsIgnoreCase("load")) {
						loadDefaultFiles();
						System.exit(0);
					} else if (input.equalsIgnoreCase("pref")) {
						new PreferenceWindow();
					} else if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("?")) {
						help();
					} else {
						new Main().print("Commands Not Found: " + input);
						help();
					}
				}
			}
		}
	}

	/**
	 * Initializes Lab 2
	 */
	private static void Lab2() {
		VendingMachine vm = new VendingMachine(10);
		if (!isDefaultConsole)
			vm.fill();
		vm.dispense();
		vm.buyAll();

	}

	/**
	 * Creates a help menu for the system console
	 */
	private static void help() {
		List<String> help = new ArrayList<String>();
		help.add("pos:MealTester");
		help.add("dob:CalculatorTester");
		help.add("bank:SavingsAccountTester");
		help.add("lab1:Lab1");
		help.add("lab2:Lab2");
		help.add("load:Load Default Settings");
		new Main().print("HELP!!!\n" + "---Here's a list of commands:");
		for (int i = 0; i < help.size(); i++) {
			new Main().print("---------" + help.get(i));
		}
	}

	/**
	 * Loads the default settings required for the program to operate properly
	 */
	private static void loadDefaultFiles() {

		String FolderName = "Settings/";
		String FileName = "Colors.ini";
		String fileContent = "bg:0x404040\nfg:0xFFFFFF\nft:12\ncbg:0x000000\ncfg:0xFFFFFF\nindex:0";
		try {
			io.TextWriter(FileName, fileContent, FolderName, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		FolderName = "DataBase/";
		FileName = "bankinfo.dat";
		fileContent = "Name0:Corey\nBalance0:300.0\nName1:Sofia\nBalance1:2000.0";
		try {
			io.TextWriter(FileName, fileContent, FolderName, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Runtime.getRuntime().exec("java -jar Assignment-Selector.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * IF NEEDED it will update the Graphics on the JFrame
	 * 
	 * @param frame
	 * @param g
	 */
	public void updateFrame(JFrame frame, Graphics g) {
		frame.update(g);
		print(frame.getTitle() + " Window Updated");
	}

	/**
	 * IF NEEDED it will update the Graphics on the JFrame
	 * 
	 * @param frame
	 */
	public void updateFrame(JFrame frame) {
		frame.update(frame.getGraphics());
		print(frame.getTitle() + " Window Updated");
	}

	/**
	 * Updates the Console GUI
	 */
	public void updateConsole() {
		console.setBackground(getConsoleBg());
		console.setForeground(getConsoleFg());

		menuBar.setForeground(getConsoleFg());
		menuBar.setBackground(getConsoleBg());

		file.setBackground(getConsoleBg());
		file.setForeground(getConsoleFg());

		edit.setForeground(getConsoleFg());
		edit.setBackground(getConsoleBg());

		exit.setForeground(getConsoleFg());
		exit.setBackground(getConsoleBg());
		// Labs Menu
		lab_1.setBackground(getConsoleBg());
		lab_1.setForeground(getConsoleFg());

		lab_2.setBackground(getConsoleBg());
		lab_2.setForeground(getConsoleFg());

		assign.setForeground(getConsoleFg());
		assign.setBackground(getConsoleBg());

		labs.setForeground(getConsoleFg());
		labs.setBackground(getConsoleBg());
		// Assignment 1 Menu
		assign_1.setForeground(getConsoleFg());
		assign_1.setBackground(getConsoleBg());

		dob.setBackground(getConsoleBg());
		dob.setForeground(getConsoleFg());

		pos.setBackground(getConsoleBg());
		pos.setForeground(getConsoleFg());

		// Assignment 2 Menu
		assign_2.setBackground(getConsoleBg());
		assign_2.setForeground(getConsoleFg());

		sat.setBackground(getConsoleBg());
		sat.setForeground(getConsoleFg());

		update.setBackground(getConsoleBg());
		update.setForeground(getConsoleFg());

		preference.setForeground(getConsoleFg());
		preference.setBackground(getConsoleBg());

		load.setBackground(getConsoleBg());
		load.setForeground(getConsoleFg());

		// Console
		console.setBackground(getConsoleBg());
		console.setForeground(getConsoleFg());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(load)) {
			loadDefaultFiles();
		} else if (e.getSource().equals(preference)) {
			new PreferenceWindow();
		} else if (e.getSource().equals(update)) {
			new Updater();
		} else if (e.getSource().equals(exit)) {
			System.exit(0);
		} else if (e.getSource().equals(dob)) {
			new CalendarTester();
		} else if (e.getSource().equals(pos)) {
			new MealTester();
		} else if (e.getSource().equals(sat)) {
			new SavingsAccountTester();
		} else if (e.getSource().equals(lab_1)) {
			new Lab1();
		} else if (e.getSource().equals(lab_2)) {
			Lab2();
		}
	}

	public static int getFontSize() {
		return fontSize;
	}

	public static void setFontSize(int size) {
		fontSize = size;
		font = new Font("Arial", Font.PLAIN, size);
		if (isDefaultConsole)
			console.setFont(font);
	}

	public Font getFont() {
		return font;
	}

	/**
	 * 
	 * @return slightly darker or lighter color based on the current background
	 *         color
	 */
	public static Color getTitleFg() {
		int r = 0, g = 0, b = 0;
		boolean rVisible = true, gVisible = true, bVisible = true;
		if (bg.getRed() > scd)
			r = bg.getRed() - scd;
		else
			rVisible = false;
		if (bg.getGreen() > scd)
			g = bg.getGreen() - scd;
		else
			gVisible = false;
		if (bg.getBlue() > scd)
			b = bg.getBlue() - scd;
		else
			bVisible = false;
		new Main().print(bg.toString());
		Color c;
		if (!rVisible && !gVisible && !bVisible)
			c = new Color(bg.getRed() + scd, bg.getGreen() + scd, bg.getBlue() + scd);
		else
			c = new Color(r, g, b);
		return c;
	}

	/**
	 * 
	 * @param bg
	 * @return slightly darker or lighter color based on the given background
	 *         color
	 */
	public static Color getTitleFg(Color bg) {
		int r = 0, g = 0, b = 0;
		boolean rVisible = true, gVisible = true, bVisible = true;
		if (bg.getRed() > scd)
			r = bg.getRed() - scd;
		else
			rVisible = false;
		if (bg.getGreen() > scd)
			g = bg.getGreen() - scd;
		else
			gVisible = false;
		if (bg.getBlue() > scd)
			b = bg.getBlue() - scd;
		else
			bVisible = false;
		new Main().print(bg.toString());
		Color c;
		if (!rVisible && !gVisible && !bVisible)
			c = new Color(bg.getRed() + scd, bg.getGreen() + scd, bg.getBlue() + scd);
		else
			c = new Color(r, g, b);
		return c;
	}

	public static JFrame getFrame() {
		return consoleWindow;
	}

	public static Color getBg() {
		return bg;
	}

	/**
	 * sets the main Background Color
	 * 
	 * @param bg
	 */
	public static void setBg(Color bg) {
		Main.bg = bg;
	}

	public static Color getFg() {
		return fg;
	}

	/**
	 * sets the main Foreground Color
	 * 
	 * @param fg
	 */
	public static void setFg(Color fg) {
		Main.fg = fg;
	}

	/**
	 * Sets the Console Foreground Color
	 * 
	 * @param fg
	 */
	public static void setConsoleFg(Color fg) {
		Main.cfg = fg;
	}

	/**
	 * @return Console Foreground Color
	 */
	public static Color getConsoleFg() {
		return cfg;
	}

	public static void setConsoleBg(Color bg) {
		Main.cbg = bg;
	}

	/**
	 * @return Console Background
	 */
	public static Color getConsoleBg() {
		return cbg;
	}

	/**
	 * Sets the Current Page Index used for the Preference Window
	 * 
	 * @param index
	 */
	public static void setIndex(int index) {
		new Main().index = index;
	}

	/**
	 * Gets the Current Preference Window Page
	 * 
	 * @return Page Index
	 */

	public static int getIndex() {
		return index;
	}

}
