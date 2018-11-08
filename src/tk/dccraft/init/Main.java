package tk.dccraft.init;

import java.awt.Color;
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
import tk.dccraft.exercises.Exercise_3;
import tk.dccraft.exercises.Exercise_4;
import tk.dccraft.exercises.Exercise_5;
import tk.dccraft.exercises.Exercise_7;
import tk.dccraft.exercises.Exercise_8;
import tk.dccraft.http.updater.Updater;
import tk.dccraft.labs.Lab1;
import tk.dccraft.labs.Lab2;
import tk.dccraft.labs.Lab3;
import tk.dccraft.labs.Lab4;
import tk.dccraft.labs.Lab5;
import tk.dccraft.utils.BIOS;
import tk.dccraft.utils.InitUtilities;
import tk.dccraft.utils.OsUtils;
import tk.dccraft.utils.settings.PreferenceWindow;

/**
 * The Core class for the program... Contains the Main Method Creates the
 * Console Window
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Main extends InitUtilities {
	public static void main(String[] args) {
		if (OsUtils.isWindows()) 
			root += "\\AppData\\Roaming\\A Drew Chase Project\\Java\\";
		 else 
			root += "/.A Drew Chase Project/Java/";
		m.print("Loading Settings for " + OsUtils.getOsName());
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
}
