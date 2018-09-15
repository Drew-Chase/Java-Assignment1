package tk.dccraft.Assignment_1.init;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import tk.dccraft.Assignment_1.part_1.MealTester;
import tk.dccraft.Assignment_1.part_2.CalendarTester;

/**
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Main {

	public static JTextArea console;
	public static JFrame consoleWindow;
	private static DefaultCaret caret;

	public static boolean isDefaultConsole;

	// Initializes the Custom Console Window
	public static void initConsoleWindow() {
		// Initializing Frame
		consoleWindow = new JFrame("Console");
		consoleWindow.setSize(new Dimension(800 / 2, 600 / 2));
		consoleWindow.setUndecorated(true);
		consoleWindow.setLocation(50, 100);
		consoleWindow.setResizable(false);

		// Initializing UI
		console = new JTextArea("");
		console.setSize(new Dimension(consoleWindow.getWidth() - 200, consoleWindow.getHeight() - 50));
		console.setBackground(Color.BLACK);
		console.setForeground(Color.WHITE);
		console.setEditable(false);

		GridBagLayout gbl = new GridBagLayout();
		consoleWindow.setLayout(gbl);

		console.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		consoleWindow.setVisible(true);
		consoleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
			isDefaultConsole = true;
			initConsoleWindow();
			new CalendarTester();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new MealTester();
		} else {
			isDefaultConsole = false;
			// Everything Below is for standard Command Line Launch
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("pos")) {
					new MealTester();
				} else if (args[0].equalsIgnoreCase("dob")) {
					new CalendarTester();
				} else {
					System.out.println("Enter pos for MealTester or dob for CalendarTester.  Not " + args[0]);
				}
			} else{
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter pos for MealTester or dob for CalendarTester");
				String input = sc.nextLine();
				if (input.equalsIgnoreCase("pos")) {
					new MealTester();
				} else if (input.equalsIgnoreCase("dob")) {
					new CalendarTester();
				} else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
					System.exit(0);
				} else {
					System.out.println("Enter pos for MealTester or dob for CalendarTester.  Not " + input);
				}
			}
		}
	}

}
