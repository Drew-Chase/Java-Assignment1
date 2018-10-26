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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
public class Main implements ActionListener, MouseListener {

	public static JTextArea console;
	private static JFrame consoleWindow;
	private static DefaultCaret caret;
	private static JMenuBar menuBar;
	private static JMenu file, edit, assign, assign_1, assign_2, assign_3, labs;
	private static JMenuItem pos, dob, key, exit, terminal, sat, update, preference, load, lab_1, lab_2, lab_3, lab_4, clear;
	private static Color bg, fg, cbg, cfg;
	private static int fontSize = 12;
	private final int STANDARD_COLOR_DIFFERENCE = 50;
	private static boolean isDefaultConsole;
	public Date date = new Date();
	private DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm");
	public DateFormat timestamp = new SimpleDateFormat("HH:mm:ss");
	private final String DATE_STRING = dateFormat.format(date.getTime());
	private static List<String> log = new ArrayList<String>();

	private static BIOS io = new BIOS();
	private static int index = 0;
	public static Font font, titleFont = new Font(new Main().initFonts("ScorchedEarth.otf").getFontName(), Font.PLAIN, 28);
	public char[] abc = ("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()).toCharArray();
	public Font styleFont;
	private static List<String> help = new ArrayList<String>();

	public static String root = System.getProperty("user.home") + "\\A Drew Chase Project\\Java\\";

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

		lab_3 = new JMenuItem("Lab 3");
		lab_3.addActionListener(new Main());
		lab_3.setBackground(getConsoleBg());
		lab_3.setForeground(getConsoleFg());
		labs.add(lab_3);

		lab_4 = new JMenuItem("Lab 4");
		lab_4.addActionListener(new Main());
		lab_4.setBackground(getConsoleBg());
		lab_4.setForeground(getConsoleFg());
		labs.add(lab_4);

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

		assign_3 = new JMenu("Assignment 3");
		assign_3.setBackground(getConsoleBg());
		assign_3.setForeground(getConsoleFg());
		assign.add(assign_3);

		key = new JMenuItem("Key Tester");
		key.addActionListener(new Main());
		key.setBackground(getConsoleBg());
		key.setForeground(getConsoleFg());
		key.setEnabled(false);
		assign_3.add(key);

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

		clear = new JMenuItem("Clear Console");
		clear.addActionListener(new Main());
		clear.setBackground(getConsoleBg());
		clear.setForeground(getConsoleFg());
		edit.add(clear);

		terminal = new JMenuItem("Open in System Terminal");
		terminal.addActionListener(new Main());
		terminal.setBackground(getConsoleBg());
		terminal.setForeground(getConsoleFg());
		edit.add(terminal);

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
		if (isDefaultConsole())
			console.append(message.toString() + "\n");
		else
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
		if (System.console() == null) {
			Main main = new Main();
			try {
				io.TextReader("Pref.ini", root + "Settings\\", "style");
				io.TextReader("bankinfo.dat", root + "Settings\\DataBase\\", "bank");
				main.setShouldLog(io.log.equalsIgnoreCase("true") ? true : false);
				main.shouldLog = (io.log.equalsIgnoreCase("true") ? true : false);
				System.out.println("setting is logging to " + main.isShouldLog());
				main.setBg(new Color(Integer.decode(io.bg)));
				main.setFg(new Color(Integer.decode(io.fg)));
				main.setFontSize(Integer.parseInt(io.ft));
				main.setIndex(Integer.parseInt(io.index));
			} catch (Exception e) {
				new Main().print("Files not found... Creating them");
				new Main().loadDefaultFiles();
			}
			setDefaultConsole(true);
			getLog().add("Starting LOG...**");
			new Main().setConsoleBg(new Color(Integer.decode(io.cbg)));
			new Main().setConsoleFg(new Color(Integer.decode(io.cfg)));
			new Main().initConsoleWindow();
			new Main().initStartMessages();
			EventQueue.invokeLater(() -> {
				consoleWindow.setVisible(true);
			});
		} else {
			setDefaultConsole(false);
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
					new Main().Lab2();
				} else if (args[0].equalsIgnoreCase("lab3")) {
					new Lab3();
				} else if (args[0].equalsIgnoreCase("lab4")) {
					new Lab4();
				} else if (args[0].equalsIgnoreCase("load")) {
					new Main().loadDefaultFiles();
					new Main().Exit();
				} else if (args[0].equalsIgnoreCase("pref")) {
					new PreferenceWindow();
				} else if (args[0].equalsIgnoreCase("update")) {
					new Updater();
				} else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
					new Main().help();
				} else {
					new Main().print(args[0] + " is not a proper argument.");
					new Main().help();
				}
			} else {
				while (true) {
					help.add("pos:MealTester");
					help.add("dob:CalculatorTester");
					help.add("bank:SavingsAccountTester");
					help.add("lab1:Lab1");
					help.add("lab2:Lab2");
					help.add("lab3:Lab3");
					help.add("lab4:lab4");
					help.add("load:Load Default Settings");
					help.add("pref:Open Prefrences");
					help.add("exit:Exits the Program");
					help.add("custom terminal | custom | ct:Opens a custom ease of use Terminal");
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
						new Main().Lab2();
					} else if (input.equalsIgnoreCase("lab3")) {
						new Lab3();
					} else if (input.equalsIgnoreCase("lab4")) {
						new Lab4();
					} else if (input.equalsIgnoreCase("bank")) {
						new SavingsAccountTester();
					} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
						new Main().Exit();
					} else if (input.equalsIgnoreCase("load")) {
						new Main().loadDefaultFiles();
						System.exit(0);
					} else if (input.equalsIgnoreCase("update")) {
						new Updater();
					} else if (input.equalsIgnoreCase("pref")) {
						new PreferenceWindow();
					} else if (input.equalsIgnoreCase("custom terminal") || input.equalsIgnoreCase("custom") || input.equalsIgnoreCase("ct")) {
						new Main().launchCustomTerminal();
					} else if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("?")) {
						new Main().help();
					} else {
						new Main().print("Commands Not Found: " + input);
						new Main().help();
					}
				}
			}
		}
		// while(true){
		// System.out.println(new Main().getIsLogging());
		// }
	}

	public void initStartMessages() {
		print("(c) A Drew Chase Project", 2);
		print("Version: " + Metadata.version);
		print("Current Author: " + Metadata.author);
		print("Changelog: Fixed the Updater, finished lab4, worked on cross commandline interfaces");
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
		String fileContent = "bg:0x404040\nfg:0xFFFFFF\nft:12\ncbg:0x000000\ncfg:0xFF00FF\nindex:0\nlog:true";
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
//			io.TextWriter("log-" + DATE_STRING + ".txt", getLog().toString().replace("[", "").replace("]", "").replace("**", "\n").replace(", ", ""), root + "\\logs\\", false);
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

		lab_3.setBackground(getConsoleBg());
		lab_3.setForeground(getConsoleFg());

		lab_4.setBackground(getConsoleBg());
		lab_4.setForeground(getConsoleFg());

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
		
		// Assignment 3
		assign_3.setBackground(getConsoleBg());
		assign_3.setForeground(getConsoleFg());

		update.setBackground(getConsoleBg());
		update.setForeground(getConsoleFg());

		preference.setForeground(getConsoleFg());
		preference.setBackground(getConsoleBg());

		load.setBackground(getConsoleBg());
		load.setForeground(getConsoleFg());

		clear.setBackground(getConsoleBg());
		clear.setForeground(getConsoleFg());

		terminal.setBackground(getConsoleBg());
		terminal.setForeground(getConsoleFg());

		// Console
		console.setBackground(getConsoleBg());
		console.setForeground(getConsoleFg());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(clear)) {
			Clear();
		} else if (e.getSource().equals(load)) {
			loadDefaultFiles();
		} else if (e.getSource().equals(preference)) {
			new PreferenceWindow();
		} else if (e.getSource().equals(terminal)) {
			launchSystemTerminal();
		} else if (e.getSource().equals(update)) {
			new Updater();
		} else if (e.getSource().equals(exit)) {
			Exit();
		} else if (e.getSource().equals(dob)) {
			new CalendarTester();
		} else if (e.getSource().equals(pos)) {
			new MealTester();
		} else if (e.getSource().equals(sat)) {
			new SavingsAccountTester();
		} else if (e.getSource().equals(key)) {
			new KeyTester();
		} else if (e.getSource().equals(lab_1)) {
			new Lab1();
		} else if (e.getSource().equals(lab_2)) {
			Lab2();
		} else if (e.getSource().equals(lab_3)) {
			CmdLabStarter("lab 3");
		} else if (e.getSource().equals(lab_4)) {
			CmdLabStarter("lab 4");
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
