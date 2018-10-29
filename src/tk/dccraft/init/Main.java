package tk.dccraft.init;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import tk.dccraft.Assignment_1.part_1.MealTester;
import tk.dccraft.Assignment_1.part_2.CalendarTester;
import tk.dccraft.Assignment_2.bank.SavingsAccountTester;
import tk.dccraft.Assignment_3.keys.KeyTester;
import tk.dccraft.Assignment_4.school.InternshipApp;
import tk.dccraft.exercises.Ex7;
import tk.dccraft.exercises.Exercise3;
import tk.dccraft.exercises.Exercise4;
import tk.dccraft.exercises.Exercise8;
import tk.dccraft.exercises.NumberGuesser;
import tk.dccraft.http.updater.Updater;
import tk.dccraft.lab1.Lab1;
import tk.dccraft.lab2.VendingMachine;
import tk.dccraft.lab3.Lab3;
import tk.dccraft.lab4.Lab4;
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
public class Main extends Listeners {

	public static JTextArea console;
	private static JFrame consoleWindow;
	private static DefaultCaret caret;
	private static JMenuBar menuBar, titleBar;
	private static List<JMenuItem> menuItems = new ArrayList<JMenuItem>();
	private static List<JMenu> menus = new ArrayList<JMenu>();
	private static List<String> menuName = new ArrayList<String>();
	private static List<String> menuItemName = new ArrayList<String>();
	private static JButton exit;
	private static Color bg, fg, cbg, cfg;
	private static int fontSize = 12;
	private final int STANDARD_COLOR_DIFFERENCE = 50;
	private static boolean isDefaultConsole;
	public Date date = new Date();
	private DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm");
	public DateFormat timestamp = new SimpleDateFormat("HH:mm:ss");
	private final String DATE_STRING = dateFormat.format(date.getTime());
	private static List<String> log = new ArrayList<String>();

	private int xOnFrame, yOnFrame;

	private static BIOS io = new BIOS();
	private static int index = 0;
	public static Font font, titleFont = new Font(/*new Main().initFonts("ScorchedEarth.otf").getFontName()*/"Arial", Font.PLAIN, 28);
	public char[] abc = ("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()).toCharArray();
	public Font styleFont;
	private static List<String> help = new ArrayList<String>();

	public static String root = System.getProperty("user.home") + "\\AppData\\Roaming\\A Drew Chase Project\\Java\\";

	public boolean shouldLog;

	/**
	 * Initializes the Custom Console Window
	 */
	public void initConsoleWindow() {
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
		titleBar = new JMenuBar();
		titleBar.setBorderPainted(false);
		titleBar.setForeground(getConsoleFg());
		titleBar.setBackground(getConsoleBg());
		titleBar.addMouseListener(this);
		titleBar.addMouseMotionListener(this);

		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(getConsoleFg());
		menuBar.setBackground(getConsoleBg());
		menuBar.addMouseListener(this);
		menuBar.addMouseMotionListener(this);

		menuName.add("File");
		menuName.add("Edit");
		menuName.add("Assignments");
		menuName.add("Assignment 1");
		menuName.add("Assignment 2");
		menuName.add("Assignment 3");
		menuName.add("Assignment 4");
		menuName.add("Labs");
		menuName.add("Exercises");

		menuItemName.add("a1:Meal Tester");
		menuItemName.add("a1:Calendar Tester");
		menuItemName.add("a2:Savings Account");
		menuItemName.add("a3:Key Breaker");
		menuItemName.add("a4:Internship App");
		menuItemName.add("l1:Print Name");
		menuItemName.add("l2:Vending Machine");
		menuItemName.add("l3:Flipping Coin");
		menuItemName.add("l4:Loops");
		menuItemName.add("ed:clear");
		menuItemName.add("ed:Update");
		menuItemName.add("ed:Open in System Terminal");
		menuItemName.add("ed:preferences");
		menuItemName.add("ed:load");
		menuItemName.add("ex:Exercise 3");
		menuItemName.add("ex:Exercise 4");
		menuItemName.add("ex:Exercise 5");
		menuItemName.add("ex:Exercise 7");
		menuItemName.add("ex:Exercise 8");

		for (int i = 0; i < menuName.size(); i++) {
			menus.add(new JMenu());
			String text = menuName.get(i).toCharArray()[0] + menuName.get(i).substring(1);
			menus.get(i).setText(text);
			menus.get(i).setBackground(getConsoleBg());
			menus.get(i).setForeground(getConsoleFg());
			menus.get(i).setText(menuName.get(i));
			menus.get(i).addActionListener(this);
			if (menuName.get(i).equalsIgnoreCase("Assignments") || menuName.get(i).equalsIgnoreCase("Labs") || menuName.get(i).equalsIgnoreCase("Exercises")) {
				menus.get(menuName.indexOf("File")).add(menus.get(i));
			} else if (menuName.get(i).startsWith("Assignment ")) {
				menus.get(menuName.indexOf("Assignments")).add(menus.get(i));
			} else {
				menuBar.add(menus.get(i));
			}
		}

		for (int i = 0; i < menuItemName.size(); i++) {
			menuItems.add(new JMenuItem());
			String text = menuItemName.get(i).substring(3);
			text = (text.toCharArray()[0] + "").toString().toUpperCase() + text.substring(1);
			menuItems.get(i).setText(text);
			menuItems.get(i).setBackground(getConsoleBg());
			menuItems.get(i).setForeground(getConsoleFg());
			menuItems.get(i).addActionListener(this);
			if (menuItemName.get(i).startsWith("a1:")) {
				menus.get(menuName.indexOf("Assignment 1")).add(menuItems.get(i));
			} else if (menuItemName.get(i).startsWith("a2:")) {
				menus.get(menuName.indexOf("Assignment 2")).add(menuItems.get(i));
			} else if (menuItemName.get(i).startsWith("a3:")) {
				menus.get(menuName.indexOf("Assignment 3")).add(menuItems.get(i));
				menuItems.get(i).setEnabled(false);
			} else if (menuItemName.get(i).startsWith("a4:")) {
				menus.get(menuName.indexOf("Assignment 4")).add(menuItems.get(i));
			} else if (menuItemName.get(i).startsWith("l")) {
				menus.get(menuName.indexOf("Labs")).add(menuItems.get(i));
			} else if (menuItemName.get(i).startsWith("ed:")) {
				menus.get(menuName.indexOf("Edit")).add(menuItems.get(i));
			} else if (menuItemName.get(i).startsWith("mi:")) {
				menuBar.add(menuItems.get(menuItemName.indexOf("mi:Exit")));
			} else if (menuItemName.get(i).startsWith("ex:")) {
				menus.get(menuName.indexOf("Exercises")).add(menuItems.get(i));
			}
		}

		exit = new JButton("Exit");
		exit.setLayout(null);
		exit.addActionListener(this);
		exit.setBackground(getConsoleBg());
		exit.setForeground(getConsoleFg());
		exit.setBorderPainted(false);
		exit.setSize(exit.getText().length() * exit.getFont().getSize(), 12);
		exit.setLocation(consoleWindow.getWidth() - exit.getWidth(), 0);
		menuBar.add(exit);

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

		styleFont = new Font(initFonts("BarcodeFont").getFontName(), Font.PLAIN, 72);

	}

	/**
	 * Prints the message to either console
	 * 
	 * @param message
	 *            gets the message to print as an object then converts it to a
	 *            printable string
	 */
	public void print(Object message) {
		getLog().add(new Main().timestamp.format(date.getTime()) + " : " + message.toString() + "**");
		if (isDefaultConsole()) {
			console.append(message.toString() + "\n");
		} else
			System.out.println(message + "\n");
	}

	/**
	 * Prints the message to either console and makes a new line base on the
	 * newLine param
	 * 
	 * @param message
	 *            gets the message to print as an object then converts it to a
	 *            printable string
	 * @param newLine
	 *            prints a x new lines after the text
	 */
	public void print(Object message, int newLine) {
		getLog().add(timestamp.format(date.getTime()) + " : " + message.toString() + "**");
		if (isDefaultConsole()) {
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

	public Font initFonts(String font) {
		Font f = new Font("Arial", Font.PLAIN, 18);
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			if (font.toLowerCase().contains(".ttf") || font.toLowerCase().contains(".otf"))
				f = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/" + font));
			else
				f = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/" + font + ".ttf"));
			ge.registerFont(f);

			print("Imported " + font);
			return f;
		} catch (Exception e) {
			print("Had issues importing " + font);
			print(e.getMessage());
			e.printStackTrace();
			return f;
		}
	}

	// public Main(){
	//
	// }

	public static void main(String[] args) {
		boolean fileFound = true;
		if (System.console() == null) {
			String FolderName = root + "Settings\\";
			String FileName = "Pref.ini";
//			Main main = new Main();
			try {
				io.TextReader(FileName, FolderName, "style");
				new Main().setBg(new Color(Integer.decode(io.bg)));
				new Main().setFg(new Color(Integer.decode(io.fg)));
				new Main().setFontSize(Integer.parseInt(io.ft));
				new Main().setIndex(Integer.parseInt(io.index));
				new Main().setConsoleBg(new Color(Integer.decode(io.cbg)));
				new Main().setConsoleFg(new Color(Integer.decode(io.cfg)));
				new Main().initConsoleWindow();
			} catch (IOException e) {
				System.out.println("HELP");
				e.printStackTrace();
//				new Main().Exit();
//				fileFound = false;
			}
			// main.setShouldLog(io.log.equalsIgnoreCase("true") ? true :
			// false);
			// main.shouldLog = (io.log.equalsIgnoreCase("true") ? true :
			// false);
			// System.out.println("setting is logging to " +
			// main.isShouldLog());
			getLog().add("Starting LOG...**");
			new Main().initStartMessages();
			EventQueue.invokeLater(() -> {
				consoleWindow.setVisible(true);
			});
			setDefaultConsole(true);
			if (!fileFound) {
				new Main().print("Files not found... Creating them");
				new Main().loadDefaultFiles();
				System.out.println("FILE NOT FOUND");
			}}
//		} else {
//			setDefaultConsole(false);
//			// Everything Below is for standard Command Line Launch
//			if (args.length > 0) {
//				if (args[0].equalsIgnoreCase("pos")) {
//					new MealTester();
//				} else if (args[0].equalsIgnoreCase("dob")) {
//					new CalendarTester();
//				} else if (args[0].equalsIgnoreCase("bank")) {
//					new SavingsAccountTester();
//				} else if (args[0].equalsIgnoreCase("lab1")) {
//					new Lab1();
//				} else if (args[0].equalsIgnoreCase("lab2")) {
//					new Main().Lab2();
//				} else if (args[0].equalsIgnoreCase("lab3")) {
//					new Lab3();
//				} else if (args[0].equalsIgnoreCase("lab4")) {
//					new Lab4();
//				} else if (args[0].equalsIgnoreCase("ex3")) {
//					new Exercise3();
//				} else if (args[0].equalsIgnoreCase("ex4")) {
//					new Exercise4();
//				} else if (args[0].equalsIgnoreCase("ex5")) {
//					new NumberGuesser();
//				} else if (args[0].equalsIgnoreCase("ex7")) {
//					new Ex7();
//				} else if (args[0].equalsIgnoreCase("ex8")) {
//					new Exercise8();
//				} else if (args[0].equalsIgnoreCase("load")) {
//					new Main().loadDefaultFiles();
//					new Main().Exit();
//				} else if (args[0].equalsIgnoreCase("pref")) {
//					new PreferenceWindow();
//				} else if (args[0].equalsIgnoreCase("update")) {
//					new Updater();
//				} else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
//					new Main().help();
//				} else if (args[0].equalsIgnoreCase("gui")) {
//					// setDefaultConsole(true);
//					getLog().add("Starting LOG...**");
//					new Main().setConsoleBg(new Color(Integer.decode(io.cbg)));
//					new Main().setConsoleFg(new Color(Integer.decode(io.cfg)));
//					new Main().initConsoleWindow();
//					new Main().initStartMessages();
//					EventQueue.invokeLater(() -> {
//						consoleWindow.setVisible(true);
//					});
//				} else {
//					new Main().print(args[0] + " is not a proper argument.");
//					new Main().help();
//				}
//			} else {
//				while (true) {
//					help.add("pos:MealTester");
//					help.add("dob:CalculatorTester");
//					help.add("bank:SavingsAccountTester");
//					help.add("lab1:Lab1");
//					help.add("lab2:Lab2");
//					help.add("lab3:Lab3");
//					help.add("lab4:lab4");
//					help.add("load:Load Default Settings");
//					help.add("pref:Open Prefrences");
//					help.add("exit:Exits the Program");
//					help.add("custom terminal | custom | ct:Opens a custom ease of use Terminal");
//					Scanner sc = new Scanner(System.in);
//					new Main().print("type help or ? for a list of commands and cmd arguments");
//					String input = sc.nextLine();
//					if (input.equalsIgnoreCase("pos")) {
//						new MealTester();
//					} else if (input.equalsIgnoreCase("dob")) {
//						new CalendarTester();
//					} else if (input.equalsIgnoreCase("lab1")) {
//						new Lab1();
//					} else if (input.equalsIgnoreCase("lab2")) {
//						new Main().Lab2();
//					} else if (input.equalsIgnoreCase("lab3")) {
//						new Lab3();
//					} else if (input.equalsIgnoreCase("lab4")) {
//						new Lab4();
//					} else if (input.equalsIgnoreCase("bank")) {
//						new SavingsAccountTester();
//					} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
//						new Main().Exit();
//					} else if (input.equalsIgnoreCase("load")) {
//						new Main().loadDefaultFiles();
//						System.exit(0);
//					} else if (input.equalsIgnoreCase("update")) {
//						new Updater();
//					} else if (input.equalsIgnoreCase("pref")) {
//						new PreferenceWindow();
//					} else if (input.equalsIgnoreCase("custom terminal") || input.equalsIgnoreCase("custom") || input.equalsIgnoreCase("ct")) {
//						new Main().launchCustomTerminal();
//					} else if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("?")) {
//						new Main().help();
//					} else {
//						new Main().print("Commands Not Found: " + input);
//						new Main().help();
//					}
//				}
//			}
//		}
	}

	public void initStartMessages() {
		print("(c) A Drew Chase Project", 2);
		print("Version: " + Metadata.version);
		print("Current Author: " + Metadata.author);
		print("Changelog: Fixed the Updater,\nWorked on a more Comprehensive Color Picker Method,\nWorked on Cross CommandLine Interfaces,\nAnd Windows are Now Allowed to be Moved");
	}

	private void launchCustomTerminal() {
		try {
			Runtime.getRuntime().exec("java -jar " + getExportedName());
			Exit();
		} catch (Exception e) {
			print("Sorry something went wrong");
		}
	}

	private void launchSystemTerminal() {
		try {
			Runtime.getRuntime().exec("cmd /c start cmd /K \"java -jar " + getExportedName() + "\"");
			Exit();
		} catch (Exception e) {
			print("Sorry something went wrong");
		}
	}

	private void CmdLabStarter(String labname) {
		try {
			Runtime.getRuntime().exec("cmd /c start cmd /K \"java -jar " + getExportedName() + " " + labname + "\"");
		} catch (Exception e) {
			print("Sorry something went wrong");
		}
	}

	/**
	 * Initializes Lab 2
	 */
	private void Lab2() {
		VendingMachine vm = new VendingMachine(10);
		if (!isDefaultConsole())
			vm.fill();
		vm.dispense();
		vm.buyAll();

	}

	/**
	 * Creates a help menu for the system console
	 */
	private void help() {
		new Main().print("HELP!!!\n" + "---Here's a list of commands:");
		for (int i = 0; i < help.size(); i++) {
			new Main().print("---------" + help.get(i));
		}
	}

	/**
	 * Loads the default settings required for the program to operate properly
	 */
	private void loadDefaultFiles() {
		// File f = new File(root);
		// if(f.mkdirs())
		String FolderName = root + "Settings\\";
		String FileName = "Pref.ini";
		String fileContent = "bg:0x441E00\nfg:0xFFFFFF\nft:12\ncbg:0x8091B\ncfg:0xcc9900\nindex:0\nlog:true";
		try {
			io.TextWriter(FileName, fileContent, FolderName, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		FolderName = root + "Settings\\DataBase\\";
		FileName = "bankinfo.dat";
		fileContent = "Name0:Corey\nBalance0:300.0\nName1:Sofia\nBalance1:2000.0";
		try {
			io.TextWriter(FileName, fileContent, FolderName, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Runtime.getRuntime().exec("java -jar " + getExportedName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Main().Exit();
	}

	public String getExportedName() {
		return new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
	}

	public String getExportedPath() {
		return Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}

	/**
	 * Exits the program and saves the log.
	 */
	public void Exit() {

		System.out.println(isShouldLog());
		// if (isShouldLog()) {
		try {
			io.TextWriter("logLatest.txt", getLog().toString().replace("[", "").replace("]", "").replace("**", "\n").replace(", ", ""), root + "\\logs\\", false);
			// io.TextWriter("log-" + DATE_STRING + ".txt",
			// getLog().toString().replace("[", "").replace("]",
			// "").replace("**", "\n").replace(", ", ""), root + "\\logs\\",
			// false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// }
		System.exit(0);
	}

	/**
	 * I just wrote this when you were talking about it. but it doesn't do
	 * anything different than the original way.
	 * 
	 * @param Object
	 *            o
	 */
	@Override
	public boolean equals(Object o) {
		return this == o;
	}

	// public void setShouldLog(boolean log) {
	// this.shouldLog = log;
	// }
	//
	// public boolean isShouldLog() {
	// return shouldLog;
	// }

	/**
	 * IF NEEDED it will update the Graphics on the JFrame
	 * 
	 * @param frame
	 *            gets window
	 * @param g
	 *            gets Graphics of window
	 */
	public void updateFrame(JFrame frame, Graphics g) {
		frame.update(g);
		print(frame.getTitle() + " Window Updated");
	}

	/**
	 * IF NEEDED it will update the Graphics on the JFrame
	 * 
	 * @param frame
	 *            gets window with graphics
	 */
	public void updateFrame(JFrame frame) {
		frame.update(frame.getGraphics());
		print(frame.getTitle() + " Window Updated");
	}

	/**
	 * Updates the Console GUI
	 */
	public void updateConsole() {
		// console.setBackground(getConsoleBg());
		// console.setForeground(getConsoleFg());
		exit.setBackground(getConsoleBg());
		exit.setForeground(getConsoleFg());

		menuBar.setForeground(getConsoleFg());
		menuBar.setBackground(getConsoleBg());

		for (JMenu m : menus) {
			m.setBackground(getConsoleBg());
			m.setForeground(getConsoleFg());
		}
		for (JMenuItem m : menuItems) {
			m.setBackground(getConsoleBg());
			m.setForeground(getConsoleFg());
		}

		// Console
		console.setBackground(getConsoleBg());
		console.setForeground(getConsoleFg());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ed:clear")))) {
				Clear();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ed:load")))) {
				loadDefaultFiles();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ed:preferences")))) {
				new PreferenceWindow();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ed:Open in System Terminal")))) {
				launchSystemTerminal();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ed:Update")))) {
				new Updater();
			} else if (e.getSource().equals(exit)) {
				Exit();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("a1:Calendar Tester")))) {
				new CalendarTester();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("a1:Meal Tester")))) {
				new MealTester();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("a2:Savings Account")))) {
				new SavingsAccountTester();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("a3:Key Breaker")))) {
				new KeyTester();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("l1:Print Name")))) {
				new Lab1();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("l2:Vending Machine")))) {
				Lab2();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("l3:Flipping Coin")))) {
				CmdLabStarter("lab3");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("l4:Loops")))) {
				CmdLabStarter("lab4");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 3")))) {
				CmdLabStarter("ex3");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 4")))) {
				CmdLabStarter("ex4");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 5")))) {
				CmdLabStarter("ex5");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 7")))) {
				CmdLabStarter("ex7");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 8")))) {
				CmdLabStarter("ex8");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("a4:Internship App")))) {
				new InternshipApp();
			}
		} catch (Exception ex) {
			print("Button Action Not Found.");
			ex.printStackTrace();
		}
	}

	public boolean isShouldLog() {
		return shouldLog;
	}

	public void setShouldLog(boolean shouldLog) {
		this.shouldLog = shouldLog;
	}

	/**
	 * Clears the console text
	 */
	public void Clear() {
		console.setText("");
	}

	/**
	 * Gets the Font Size
	 * 
	 * @return font size
	 */
	public static int getFontSize() {
		return fontSize;
	}

	/**
	 * Sets the font size
	 * 
	 * @param size
	 *            font size
	 */
	public static void setFontSize(int size) {
		fontSize = size;
		font = new Font("Arial", Font.PLAIN, size);
		if (isDefaultConsole())
			console.setFont(font);
	}

	/**
	 * Gets the specified font
	 * 
	 * @return default font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * 
	 * @return slightly darker or lighter color based on the current background
	 *         color
	 */
	public Color getTitleFg() {
		int r = 0, g = 0, b = 0;
		boolean rVisible = true, gVisible = true, bVisible = true;
		if (bg.getRed() > STANDARD_COLOR_DIFFERENCE)
			r = bg.getRed() - STANDARD_COLOR_DIFFERENCE;
		else
			rVisible = false;
		if (bg.getGreen() > STANDARD_COLOR_DIFFERENCE)
			g = bg.getGreen() - STANDARD_COLOR_DIFFERENCE;
		else
			gVisible = false;
		if (bg.getBlue() > STANDARD_COLOR_DIFFERENCE)
			b = bg.getBlue() - STANDARD_COLOR_DIFFERENCE;
		else
			bVisible = false;
		new Main().print(bg.toString());
		Color c;
		if (!rVisible && !gVisible && !bVisible)
			c = new Color(bg.getRed() + STANDARD_COLOR_DIFFERENCE, bg.getGreen() + STANDARD_COLOR_DIFFERENCE, bg.getBlue() + STANDARD_COLOR_DIFFERENCE);
		else
			c = new Color(r, g, b);
		return c;
	}

	/**
	 * 
	 * @param bg
	 *            specifies the background color
	 * @return slightly darker or lighter color based on the given background
	 *         color
	 */
	public Color getTitleFg(Color bg) {
		int r = 0, g = 0, b = 0;
		boolean rVisible = true, gVisible = true, bVisible = true;
		if (bg.getRed() > STANDARD_COLOR_DIFFERENCE)
			r = bg.getRed() - STANDARD_COLOR_DIFFERENCE;
		else
			rVisible = false;
		if (bg.getGreen() > STANDARD_COLOR_DIFFERENCE)
			g = bg.getGreen() - STANDARD_COLOR_DIFFERENCE;
		else
			gVisible = false;
		if (bg.getBlue() > STANDARD_COLOR_DIFFERENCE)
			b = bg.getBlue() - STANDARD_COLOR_DIFFERENCE;
		else
			bVisible = false;
		Color c;
		if (!rVisible && !gVisible && !bVisible)
			c = new Color(bg.getRed() + STANDARD_COLOR_DIFFERENCE, bg.getGreen() + STANDARD_COLOR_DIFFERENCE, bg.getBlue() + STANDARD_COLOR_DIFFERENCE);
		else
			c = new Color(r, g, b);
		return c;
	}

	/**
	 * Gets the Console Window
	 * 
	 * @return Console Window
	 */
	public JFrame getFrame() {
		return consoleWindow;
	}

	/**
	 * Gets Background color of the default windows
	 * 
	 * @return background color
	 */
	public Color getBg() {
		return bg;
	}

	/**
	 * sets the main Background Color
	 * 
	 * @param bg
	 *            background color
	 */
	public static void setBg(Color bg) {
		Main.bg = bg;
	}

	/**
	 * Gets the Foreground Color
	 * 
	 * @return Foreground Color
	 */
	public Color getFg() {
		return fg;
	}

	/**
	 * Sets the main Foreground Color
	 * 
	 * @param fg
	 *            Foreground Color
	 */
	public static void setFg(Color fg) {
		Main.fg = fg;
	}

	/**
	 * Sets the Console Foreground Color
	 * 
	 * @param fg
	 *            Foreground Color
	 */
	public static void setConsoleFg(Color fg) {
		Main.cfg = fg;
	}

	/**
	 * Gets Console Foreground Color
	 * 
	 * @return Console Foreground Color
	 */
	public Color getConsoleFg() {
		return cfg;
	}

	public static void setConsoleBg(Color bg) {
		Main.cbg = bg;
	}

	/**
	 * Gets Console Background Color
	 * 
	 * @return Console Background
	 */
	public Color getConsoleBg() {
		return cbg;
	}

	/**
	 * Sets the Current Page Index used for the Preference Window
	 * 
	 * @param index
	 *            tab index
	 */
	public void setIndex(int index) {
		new Main().index = index;
	}

	/**
	 * Gets the Current Preference Window Page
	 * 
	 * @return Page Index
	 */

	public int getIndex() {
		return index;
	}

	public static boolean isDefaultConsole() {
		return isDefaultConsole;
	}

	public static void setDefaultConsole(boolean isDefaultConsole) {
		Main.isDefaultConsole = isDefaultConsole;
	}

	public static List<String> getLog() {
		return log;
	}

	public static void addLog(String log) {
		Main.log.add(log);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// if (e.getSource().equals()) {
		xOnFrame = e.getX();
		yOnFrame = e.getY();
		// }
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// if (e.getSource().equals(menus.get(menuName.indexOf("Move")))) {
		int x = (int) e.getXOnScreen() - xOnFrame;
		int y = (int) e.getYOnScreen() - yOnFrame;
		consoleWindow.setLocation(x, y);
		// }
	}

}
