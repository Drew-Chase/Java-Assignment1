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
import tk.dccraft.http.updater.Download;
import tk.dccraft.lab1.Lab1;
import tk.dccraft.lab2.VendingMachine;
import tk.dccraft.utils.TextTransfer;
import tk.dccraft.utils.settings.PreferenceWindow;

/**
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Main implements ActionListener {

	public static JTextArea console;
	public static JFrame consoleWindow;
	private static DefaultCaret caret;
	private static JMenuBar menuBar;
	private static JMenu file, edit, assign_1, assign_2;
	private static JMenuItem pos, dob, exit, sat, update, preference, load;
	private static Color bg, fg;
	private static int fontSize = 12;
	private static boolean isDefaultConsole;

	public static Font font;

	// Initializes the Custom Console Window
	public static void initConsoleWindow() {
		// Initializing Frame
		consoleWindow = new JFrame("Console");
		consoleWindow.setSize(new Dimension(500, 300));
		consoleWindow.setUndecorated(true);
		consoleWindow.setLocation(50, 100);
		consoleWindow.setResizable(true);
		consoleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		consoleWindow.setLayout(new FlowLayout());
		consoleWindow.setBackground(Color.BLACK);

		// Initializing UI
		// Menu Items
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);

		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		menuBar.add(file);

		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		edit.addActionListener(new Main());
		menuBar.add(edit);

		exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addActionListener(new Main());
		menuBar.add(exit);

		// Assignment 1 Menu
		assign_1 = new JMenu("Assignment 1");
		file.add(assign_1);

		dob = new JMenuItem("CalendarTester");
		dob.addActionListener(new Main());
		assign_1.add(dob);

		pos = new JMenuItem("MealTester");
		pos.addActionListener(new Main());
		assign_1.add(pos);

		// Assignment 2 Menu
		assign_2 = new JMenu("Assignment 2");
		file.add(assign_2);

		sat = new JMenuItem("Savings Account Tester");
		sat.addActionListener(new Main());
		assign_2.add(sat);

		update = new JMenuItem("Update");
		update.addActionListener(new Main());
		file.add(update);

		// Working on Edit Menu
		preference = new JMenuItem("Preferences");
		preference.addActionListener(new Main());
		edit.add(preference);

		load = new JMenuItem("Load Default Files");
		load.addActionListener(new Main());
		edit.add(load);

		// Console
		console = new JTextArea("");
		console.setSize(new Dimension(consoleWindow.getWidth() - 200, consoleWindow.getHeight() - 50));
		console.setBackground(Color.BLACK);
		console.setForeground(Color.WHITE);
		console.setEditable(false);

		GridBagLayout gbl = new GridBagLayout();
		consoleWindow.setLayout(gbl);

		// console.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		caret = (DefaultCaret) console.getCaret();
		caret.setUpdatePolicy(2);
		scroll.setForeground(Color.LIGHT_GRAY);
		scroll.setBackground(Color.black);
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.insets = new Insets(0, 0, 5, 5);
		scrollConstraints.fill = 1;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.gridwidth = 3;
		scrollConstraints.gridheight = 2;
		scrollConstraints.weightx = 1.0;
		scrollConstraints.weighty = 1.0;
		scrollConstraints.insets = new Insets(0, 5, 0, 0);
		consoleWindow.add(scroll, scrollConstraints);
		consoleWindow.setJMenuBar(menuBar);

	}

	// This will print to the custom console
	public void print(Object message) {
		if (isDefaultConsole) {
			console.append(message.toString() + "\n");
		} else {
			System.out.println(message + "\n");
		}
	}

	public static void main(String[] args) {
		if (System.console() == null) {
			initConsoleWindow();//NECESSARY
			new Main().print("IF THIS IS THE FIRST LAUNCH PLEASE RUN:\n----"+edit.getText()+"\n----------"+load.getText()+"\nTO GENERATE THE NECESSARY SETTINGS");
			TextTransfer tf = new TextTransfer();
			try {
				tf.TextReader("Colors.ini", "Settings/", "style");
				setBg(new Color(Integer.decode(tf.bg)));
				setFg(new Color(Integer.decode(tf.fg)));
				setFontSize(Integer.parseInt(tf.ft));
			} catch (IOException e) {
				e.printStackTrace();
			}
			EventQueue.invokeLater(() -> {
				consoleWindow.setVisible(true);
			});
			isDefaultConsole = true;
		} else {
			isDefaultConsole = false;
			// Everything Below is for standard Command Line Launch
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("pos")) {
					new MealTester();
				} else if (args[0].equalsIgnoreCase("dob")) {
					new CalendarTester();
				}else if (args[0].equalsIgnoreCase("bank")) {
					new SavingsAccountTester();
				}else if(args[0].equalsIgnoreCase("lab1")) {
					new Lab1();
				}else if(args[0].equalsIgnoreCase("lab2")) {
					Lab2();
				} else {
					help();
					new Main().print(args[0]+" is not a proper argument.");
				}
			} else {
				Scanner sc = new Scanner(System.in);
				new Main().print("Enter pos for MealTester or dob for CalendarTester");
				String input = sc.nextLine();
				if (input.equalsIgnoreCase("pos")) {
					new MealTester();
				} else if (input.equalsIgnoreCase("dob")) {
					new CalendarTester();
				} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
					System.exit(0);
				} else if(input.equalsIgnoreCase("help")||input.equalsIgnoreCase("?")){
					help();
				}else {
					new Main().print("Enter pos for MealTester or dob for CalendarTester.  Not " + input);
				}
			}
		}
	}
	
	private static void Lab2() {
		VendingMachine vm = new VendingMachine(10);
		vm.buyAll();
		vm.fill();
		vm.dispense();
		vm.buyAll();
		
	}
	
	private static void help() {
		new Main().print("HELP!!!\n"
				+ "---Here's a list of commands:\n"
				+ "---------pos:MealTester\n"
				+ "---------dob:CalculatorTester\n"
				+ "---------bank:SavingsAccountTester\n"
				+ "---------lab1:Lab1\n"
				+ "---------lab2:Lab2\n");
	}

	private static void loadDefaultFiles() {
		TextTransfer tf = new TextTransfer();
		String FolderName = "Settings/";
		String FileName = "Colors.ini";
		String fileContent = "bg:0x404040\nfg:0xFFFFFF\nft:12";
		try {
			tf.TextWriter(FileName, fileContent, FolderName, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		FolderName = "DataBase/";
		FileName = "bankinfo.dat";
		fileContent = "Name0:Corey\nBalance0:300.0\nName1:Sofia\nBalance1:2000.0";
		try {
			tf.TextWriter(FileName, fileContent, FolderName, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// IF NEEDED it will update the Graphics on the JFrame
	public void updateFrame(JFrame frame, Graphics g) {
		frame.update(g);
		print(frame.getTitle() + " Window Updated");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(load)) {
			 loadDefaultFiles();
		} else if (e.getSource().equals(preference)) {
			new PreferenceWindow();
		} else if (e.getSource().equals(update)) {
			new Download();
		} else if (e.getSource().equals(exit)) {
			System.exit(0);
		} else if (e.getSource().equals(dob)) {
			new CalendarTester();
		} else if (e.getSource().equals(pos)) {
			new MealTester();
		} else if (e.getSource().equals(sat)) {
			new SavingsAccountTester();
		}
	}

	public static int getFontSize() {
		return fontSize;
	}

	public static void setFontSize(int size) {
		fontSize = size;
		font = new Font("Arial", Font.PLAIN, size);
		console.setFont(font);
	}

	public Font getFont() {
		return font;
	}

	public static Color getTitleFg() {
		int r = 0, g = 0, b = 0, scd = 100;
		boolean rVisible = true, gVisible = true, bVisible = true;
		if (bg.getRed() > scd)
			r = scd;
		else
			rVisible = false;
		if (bg.getGreen() > scd)
			g = scd;
		else
			gVisible = false;
		if (bg.getBlue() > scd)
			b = scd;
		else
			bVisible = false;
		new Main().print(bg.toString());
		Color c;
		if (!rVisible && !gVisible && !bVisible)
			c = Color.WHITE;
		else
			c = new Color(r, g, b);
		return c;
	}

	public static Color getBg() {
		return bg;
	}

	public static void setBg(Color bg) {
		Main.bg = bg;
	}

	public static Color getFg() {
		return fg;
	}

	public static void setFg(Color fg) {
		Main.fg = fg;
	}

}
