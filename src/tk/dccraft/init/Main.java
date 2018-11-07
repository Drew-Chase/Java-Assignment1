package tk.dccraft.init;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import tk.dccraft.Assignment_1.part_1.MealTester;
import tk.dccraft.Assignment_1.part_2.CalendarTester;
import tk.dccraft.Assignment_2.bank.SavingsAccountTester;
import tk.dccraft.Assignment_3.keys.KeyTester;
import tk.dccraft.Assignment_4.school.InternshipApp;
import tk.dccraft.exercises.Exercise_7;
import tk.dccraft.exercises.Exercise_3;
import tk.dccraft.exercises.Exercise_4;
import tk.dccraft.exercises.Exercise_8;
import tk.dccraft.exercises.Exercise_5;
import tk.dccraft.http.updater.Updater;
import tk.dccraft.labs.Lab1;
import tk.dccraft.labs.Lab2;
import tk.dccraft.labs.Lab3;
import tk.dccraft.labs.Lab4;
import tk.dccraft.labs.Lab5;
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

	public static Terminal terminal;
	private Thread thread = new Thread();
	public static JTextArea console;
	public static Main m = new Main();
	private static JFrame consoleWindow;
	private static List<JMenuItem> menuItems;
	private static List<JMenu> menus;
	private static List<String> menuName;
	private static List<String> menuItemName;
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

	private static Scanner sc = new Scanner(System.in);
	private int xOnFrame, yOnFrame;

	private static BIOS io = new BIOS();
	private static int index = 0;
	public static Font font, titleFont = new Font(m.initFonts("ScorchedEarth.otf").getFontName(), Font.PLAIN, 28);
	public char[] abc = ("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()).toCharArray();
	public Font styleFont;
	private static List<String> help = new ArrayList<String>();

	public static String root = System.getProperty("user.home") + "\\AppData\\Roaming\\A Drew Chase Project\\Java\\";

	public boolean shouldLog;

	/**
	 * Prints the message to either console
	 * 
	 * @param message gets the message to print as an object then converts it to a
	 *                printable string
	 */
	public void print(Object message) {
		getLog().add(m.timestamp.format(date.getTime()) + " : " + message.toString() + "**");
		char[] typewritter = message.toString().toCharArray();
		if (isDefaultConsole()) {
			int counter = 0;
			for (char c : typewritter) {
				console.append(c + "");
				for (int i = 0; i < 1000; i++) {
					// console.append("0");
				}
			}
			console.append("\n");
		} else {
			for (char c : typewritter) {
				System.out.print(c);
				try {
					thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
	}

	/**
	 * Prints the message to either console and makes a new line base on the newLine
	 * param
	 * 
	 * @param message gets the message to print as an object then converts it to a
	 *                printable string
	 * @param newLine prints a x new lines after the text
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

	public static void main(String[] args) {
		boolean fileFound = true;// System.console() == null
		if (System.console() == null) {
			String FolderName = root + "Settings\\";
			String FileName = "Pref.ini";
			try {
				io.TextReader(FileName, FolderName, "style");
				m.setBg(new Color(Integer.decode(io.bg)));
				m.setFg(new Color(Integer.decode(io.fg)));
				m.setFontSize(Integer.parseInt(io.ft));
				m.setIndex(Integer.parseInt(io.index));
				m.setConsoleBg(new Color(Integer.decode(io.cbg)));
				m.setConsoleFg(new Color(Integer.decode(io.cfg)));
				terminal = new Terminal();
				consoleWindow = terminal.getFrame();
				menuItems = terminal.menuItems;
				menus = terminal.menus;
				menuName = terminal.menuName;
				menuItemName = terminal.menuItemName;
				exit = terminal.exit;
				console = terminal.console;
			} catch (Exception e) {
				m.print("ERROR Message: " + e.getMessage());
			}
			setDefaultConsole(true);
			getLog().add("Starting LOG...**");
			m.initStartMessages();

			if (!fileFound) {
				m.print("Files not found... Creating them");
				m.loadDefaultFiles();
				System.out.println("FILE NOT FOUND");
			}
		} else {
			setDefaultConsole(false);
			// Everything Below is for standard Command Line Launch
			String FolderName = root + "Settings\\";
			String FileName = "Pref.ini";
			try {
				io.TextReader(FileName, FolderName, "style");
				m.setBg(new Color(Integer.decode(io.bg)));
				m.setFg(new Color(Integer.decode(io.fg)));
				m.setFontSize(Integer.parseInt(io.ft));
				m.setIndex(Integer.parseInt(io.index));
				m.setConsoleBg(new Color(Integer.decode(io.cbg)));
				m.setConsoleFg(new Color(Integer.decode(io.cfg)));
			} catch (Exception e) {
				m.print("ERROR Message: " + e.getMessage());
			}
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
					m.Lab2();
				} else if (args[0].equalsIgnoreCase("lab3")) {
					new Lab3();
				} else if (args[0].equalsIgnoreCase("lab4")) {
					new Lab4();
				} else if (args[0].equalsIgnoreCase("ex3")) {
					new Exercise_3();
				} else if (args[0].equalsIgnoreCase("ex4")) {
					new Exercise_4();
				} else if (args[0].equalsIgnoreCase("ex5")) {
					new Exercise_5();
				} else if (args[0].equalsIgnoreCase("ex7")) {
					new Exercise_7();
				} else if (args[0].equalsIgnoreCase("ex8")) {
					new Exercise_8();
				} else if (args[0].equalsIgnoreCase("load")) {
					m.loadDefaultFiles();
					m.Exit();
				} else if (args[0].equalsIgnoreCase("pref")) {
					new PreferenceWindow();
				} else if (args[0].equalsIgnoreCase("update")) {
					new Updater();
				} else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
					m.help();
				} else {
					m.print(args[0] + " is not a proper argument.");
					m.help();
				}
			} else {
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
				m.print("type help or ? for a list of commands and cmd arguments");
				String input;
				while (sc.hasNext()) {
					// m.print(">", 0);
					input = sc.nextLine();
					if (input.equalsIgnoreCase("pos")) {
						new MealTester();
					} else if (input.equalsIgnoreCase("dob")) {
						new CalendarTester();
					} else if (input.equalsIgnoreCase("lab1")) {
						new Lab1();
					} else if (input.equalsIgnoreCase("lab2")) {
						m.Lab2();
					} else if (input.equalsIgnoreCase("lab3")) {
						new Lab3();
					} else if (input.equalsIgnoreCase("lab4")) {
						new Lab4();
					} else if (input.equalsIgnoreCase("bank")) {
						new SavingsAccountTester();
					} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
						m.Exit();
					} else if (input.equalsIgnoreCase("load")) {
						m.loadDefaultFiles();
						System.exit(0);
					} else if (input.equalsIgnoreCase("update")) {
						new Updater();
					} else if (input.equalsIgnoreCase("pref")) {
						new PreferenceWindow();
					} else if (input.equalsIgnoreCase("custom terminal") || input.equalsIgnoreCase("custom")
							|| input.equalsIgnoreCase("ct")) {
						m.launchCustomTerminal();
					} else if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("?")) {
						m.help();
					} else {
						m.print("Commands Not Found: " + input);
						m.help();
					}
				}
				sc.close();
			}
		}
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
			Runtime.getRuntime().exec("cmd /c start cmd  /c \"java -jar " + getExportedName() + "\"");
			Exit();
		} catch (Exception e) {
			print("Sorry something went wrong");
		}
	}

	private void CmdLabStarter(String labname) {
		try {
			Runtime.getRuntime().exec("cmd /c start cmd /c \"java -jar " + getExportedName() + " " + labname + "\"");
		} catch (Exception e) {
			print("Sorry something went wrong");
		}
	}

	/**
	 * Initializes Lab 2
	 */
	private void Lab2() {
		Lab2 vm = new Lab2(10);
		if (!isDefaultConsole())
			vm.fill();
		vm.dispense();
		vm.buyAll();

	}

	/**
	 * Creates a help menu for the system console
	 */
	private void help() {
		m.print("HELP!!!\n" + "---Here's a list of commands:");
		for (int i = 0; i < help.size(); i++) {
			m.print("---------" + help.get(i));
		}
	}

	/**
	 * Loads the default settings required for the program to operate properly
	 */
	public void loadDefaultFiles() {
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
		m.Exit();
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

		// System.out.println(isShouldLog());
		try {
			io.TextWriter("logLatest.txt",
					getLog().toString().replace("[", "").replace("]", "").replace("**", "\n").replace(", ", ""),
					root + "\\logs\\", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * I just wrote this when you were talking about it. but it doesn't do anything
	 * different than the original way.
	 * 
	 * @param Object o
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
	 * @param frame gets window
	 * @param g     gets Graphics of window
	 */
	public void updateFrame(JFrame frame, Graphics g) {
		frame.update(g);
		print(frame.getTitle() + " Window Updated");
	}

	/**
	 * IF NEEDED it will update the Graphics on the JFrame
	 * 
	 * @param frame gets window with graphics
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

		terminal.menuBar.setForeground(getConsoleFg());
		terminal.menuBar.setBackground(getConsoleBg());

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
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("l5:Arrays")))) {
				new Lab5();
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 3")))) {
				CmdLabStarter("ex3");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 4")))) {
				CmdLabStarter("ex4");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 5")))) {
				CmdLabStarter("ex5");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 7")))) {
				CmdLabStarter("ex7");
			} else if (e.getSource().equals(menuItems.get(menuItemName.indexOf("ex:Exercise 8")))) {
				new Exercise_8();
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
	 * @param size font size
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
		m.print(bg.toString());
		Color c;
		if (!rVisible && !gVisible && !bVisible)
			c = new Color(bg.getRed() + STANDARD_COLOR_DIFFERENCE, bg.getGreen() + STANDARD_COLOR_DIFFERENCE,
					bg.getBlue() + STANDARD_COLOR_DIFFERENCE);
		else
			c = new Color(r, g, b);
		return c;
	}

	/**
	 * 
	 * @param bg specifies the background color
	 * @return slightly darker or lighter color based on the given background color
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
			c = new Color(bg.getRed() + STANDARD_COLOR_DIFFERENCE, bg.getGreen() + STANDARD_COLOR_DIFFERENCE,
					bg.getBlue() + STANDARD_COLOR_DIFFERENCE);
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
	 * @param bg background color
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
	 * @param fg Foreground Color
	 */
	public static void setFg(Color fg) {
		Main.fg = fg;
	}

	/**
	 * Sets the Console Foreground Color
	 * 
	 * @param fg Foreground Color
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
	 * @param index tab index
	 */
	public void setIndex(int index) {
		m.index = index;
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
