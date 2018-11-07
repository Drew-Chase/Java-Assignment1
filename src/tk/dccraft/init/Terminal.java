package tk.dccraft.init;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import tk.dccraft.utils.ComponentResizer;

public class Terminal extends CustomWindow {

	public static JTextArea console;
	private static JFrame consoleWindow;
	private static DefaultCaret caret;
	public static JMenuBar menuBar, titleBar;
	public static List<JMenuItem> menuItems = new ArrayList<JMenuItem>();
	public static List<JMenu> menus = new ArrayList<JMenu>();
	public static List<String> menuName = new ArrayList<String>();
	public static List<String> menuItemName = new ArrayList<String>();
	public static JButton exit;

	Dimension size = new Dimension(440, 340);

	public Terminal() {

		// Initializing Frame
		consoleWindow = createWindow(size, "Console", new Point(50, 100));
		consoleWindow.setResizable(true);
		consoleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		consoleWindow.setBackground(getConsoleBg());
		consoleWindow.setForeground(getConsoleFg());
		ComponentResizer cr = new ComponentResizer();
		cr.registerComponent(consoleWindow);

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
		menuItemName.add("l5:Arrays");
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
		console = new JTextArea();
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

		EventQueue.invokeLater(() -> {
			consoleWindow.setVisible(true);
		});

	}

}
