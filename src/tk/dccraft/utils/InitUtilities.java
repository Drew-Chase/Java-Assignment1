package tk.dccraft.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
import tk.dccraft.exercises.Exercise_8;
import tk.dccraft.http.updater.Updater;
import tk.dccraft.init.Main;
import tk.dccraft.init.Metadata;
import tk.dccraft.init.Terminal;
import tk.dccraft.labs.Lab1;
import tk.dccraft.labs.Lab2;
import tk.dccraft.labs.Lab5;
import tk.dccraft.utils.settings.PreferenceWindow;

public class InitUtilities implements ActionListener, MouseListener, MouseMotionListener, FocusListener {

	public static Terminal terminal;
	public Thread thread = new Thread();
	public static JTextArea console;
	public static Main m = new Main();
	public static JFrame consoleWindow;
	public static List<JMenuItem> menuItems;
	public static List<JMenu> menus;
	public static List<String> menuName;
	public static List<String> menuItemName;
	public static JButton exit;
	public static Color bg, fg, cbg, cfg;
	public static int fontSize = 12;
	public final int STANDARD_COLOR_DIFFERENCE = 50;
	public static boolean isDefaultConsole;
	public Date date = new Date();
	public DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH-mm");
	public DateFormat timestamp = new SimpleDateFormat("HH:mm:ss");
	public final String DATE_STRING = dateFormat.format(date.getTime());
	public static List<String> log = new ArrayList<String>();

	public static Scanner sc = new Scanner(System.in);
	public int xOnFrame, yOnFrame;

	public static BIOS io = new BIOS();
	public static int index = 0;
	public static Font font, titleFont = new Font(m.initFonts("ScorchedEarth.otf").getFontName(), Font.PLAIN, 28);
	public char[] abc = ("abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase()).toCharArray();
	public Font styleFont;
	public static List<String> help = new ArrayList<String>();

	public static String root = System.getProperty("user.home");

	public boolean shouldLog;

	public void initStartMessages() {
		print("(c) A Drew Chase Project", 2);
		print("Version: " + Metadata.version);
		print("Current Author: " + Metadata.author);
		print("Changelog: Fixed the Updater,\nWorked on a more Comprehensive Color Picker Method,\nWorked on Cross CommandLine Interfaces,\nAnd Windows are Now Allowed to be Moved");
	}

	public void launchCustomTerminal() {
		try {
			Runtime.getRuntime().exec("java -jar " + getExportedName());
			Exit();
		} catch (Exception e) {
			print("Sorry something went wrong");
		}
	}

	public void launchSystemTerminal() {
		if (OsUtils.isWindows()) {
			try {
				Runtime.getRuntime().exec("cmd /c start cmd  /c \"java -jar " + getExportedName() + "\"");
				Exit();
			} catch (Exception e) {
				print("Sorry something went wrong");
			}
		} else if(OsUtils.isLinux()){
			if (OsUtils.isGNOME()) {
				try {
					Runtime.getRuntime().exec("gnome-terminal -e \"java -jar " + getExportedName() + "\"");
					Exit();
				} catch (Exception e) {
					print("Sorry something went wrong");
				}
			} else if (OsUtils.isKDE()) {
				try {
					Runtime.getRuntime().exec("kde-terminal -e \"java -jar " + getExportedName() + "\"");
					Exit();
				} catch (Exception e) {
					print("Sorry something went wrong");
				}
			} else {
				print("Unrecognized Desktop Environment: " + OsUtils.getDesktopEnvironment());
			}
		}else{
			
		}
	}

	public void CmdLabStarter(String labname) {
		if (OsUtils.isWindows()) {
			try {
				Runtime.getRuntime().exec("cmd /c start cmd /c \"java -jar " + getExportedName() + " " + labname + "\"");
			} catch (Exception e) {
				print("Sorry something went wrong");
			}
		} else {
			if (OsUtils.isGNOME()) {
				try {
					Runtime.getRuntime().exec("gnome-terminal -e \"java -jar " + getExportedName() + " " + labname + "\"");
					Exit();
				} catch (Exception e) {
					print("Sorry something went wrong");
				}
			} else if (OsUtils.isKDE()) {
				try {
					Runtime.getRuntime().exec("kde-terminal -e \"java -jar " + getExportedName() + " " + labname + "\"");
					Exit();
				} catch (Exception e) {
					print("Sorry something went wrong");
				}
			} else {
				print("Unrecognized Desktop Environment: " + OsUtils.getDesktopEnvironment());
			}
		}
	}

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
	 * Loads the default settings required for the program to operate properly
	 */
	public void loadDefaultFiles() {
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
			io.TextWriter("logLatest.txt", getLog().toString().replace("[", "").replace("]", "").replace("**", "\n").replace(", ", ""), root + "\\logs\\", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * Prints the message to either console
	 * 
	 * @param message
	 *            gets the message to print as an object then converts it to a
	 *            printable string
	 */
	public void print(Object message) {
		getLog().add(m.timestamp.format(date.getTime()) + " : " + message.toString() + "**");
		char[] typewritter = message.toString().toCharArray();
		if (isDefaultConsole()) {
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
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
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

	/**
	 * Initializes Lab 2
	 */
	public void Lab2() {
		Lab2 vm = new Lab2(10);
		if (!isDefaultConsole())
			vm.fill();
		vm.dispense();
		vm.buyAll();

	}

	/**
	 * Creates a help menu for the system console
	 */
	public void help() {
		m.print("HELP!!!\n" + "---Here's a list of commands:");
		for (int i = 0; i < help.size(); i++) {
			m.print("---------" + help.get(i));
		}
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
		InitUtilities.index = index;
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
		m.print(bg.toString());
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

	@Override
	public void focusGained(FocusEvent paramFocusEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent paramFocusEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

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

	public Terminal getTerminal() {
		return Terminal.term;
	}

}
