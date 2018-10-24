package tk.dccraft.Assignment_2.bank;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tk.dccraft.init.Main;
import tk.dccraft.utils.BIOS;

/**
 * Creates the GUI for the banking system
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class SavingsAccountTester extends JFrame implements ActionListener, FocusListener, MouseListener {

	private JTextField nameBox, balanceBox;
	private JPanel contentPane;
	private JButton confirm, close, getBalance, getUser, list;
	private int width, height;
	private double balance;
	private String name, initNameBox = "Enter Your Name", initBalanceBox = "Enter Your Initial Deposit";
	private NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
	private Main main;
	private JLabel title, stylelbl;
	private BIOS io = new BIOS();

	public static List<SavingsAccount> accounts = new ArrayList<SavingsAccount>();

	public SavingsAccountTester() {

		ReadFromFile();

		main = new Main();
		Color bg = main.getBg(), fg = main.getFg();

		// Size init
		width = 800;
		height = 280;
		Dimension size = new Dimension(width, height);

		// GUI Init
		contentPane = new JPanel();
		contentPane.setSize(width - 50, width - 50);
		contentPane.setBackground(bg);
		contentPane.setForeground(fg);
		contentPane.setLayout(null);

		// Stylized title for the GUI
		title = new JLabel("Test Bank title".toUpperCase());
		title.setLocation(25, -5);
		title.setForeground(main.getTitleFg());
		title.setFont(main.titleFont);
		title.setVisible(true);
		title.setSize(width / 3, 100);
		contentPane.add(title);

		stylelbl = new JLabel("Hello");
		stylelbl.setForeground(Color.WHITE);
		stylelbl.setFont(new Font(new Main().initFonts("BarcodeFont").getFontName(), Font.PLAIN, 72));
		stylelbl.setText(randomBarcode());
		stylelbl.setVisible(true);
		stylelbl.setSize(width, 80);
		stylelbl.setLayout(null);
		stylelbl.addMouseListener(this);
		stylelbl.setLocation((title.getLocation().x + title.getWidth()) + 150, title.getLocation().y);
		contentPane.add(stylelbl);

		// Input-type Init
		{
			nameBox = new JTextField(initNameBox);
			nameBox.setSize(200, 25);
			nameBox.setVisible(true);
			nameBox.setLocation(150, height / 3);
			nameBox.addActionListener(this);
			nameBox.addFocusListener(this);
			nameBox.setLayout(null);
			contentPane.add(nameBox);

			balanceBox = new JTextField(initBalanceBox);
			balanceBox.setSize(200, 25);
			balanceBox.setVisible(true);
			balanceBox.setLocation(450, height / 3);
			balanceBox.addFocusListener(this);
			balanceBox.setLayout(null);
			contentPane.add(balanceBox);
		}

		// ActionListeners
		{
			confirm = new JButton("Send");
			confirm.setToolTipText("This sends your information to the system");
			confirm.setSize(100, 50);
			confirm.setLocation(75, height - 100);
			confirm.addActionListener(this);
			confirm.setLayout(null);
			confirm.setBackground(bg);
			confirm.setForeground(fg);
			confirm.setBorderPainted(false);
			confirm.addMouseListener(this);
			contentPane.add(confirm);

			list = new JButton("List All Accounts");
			list.setToolTipText("This gets information from the system");
			list.setSize(150, 50);
			list.setLocation(175, height - 100);
			list.addActionListener(this);
			list.setLayout(null);
			list.setBackground(bg);
			list.setForeground(fg);
			list.setBorderPainted(false);
			list.addMouseListener(this);
			contentPane.add(list);

			getBalance = new JButton("Check Balance");
			getBalance.setToolTipText("Checks and Prints the Balance from a users profile");
			getBalance.setSize(150, 50);
			getBalance.setLocation(175 + 150, height - 100);
			getBalance.addActionListener(this);
			getBalance.setLayout(null);
			getBalance.setBackground(bg);
			getBalance.setForeground(fg);
			getBalance.setBorderPainted(false);
			getBalance.addMouseListener(this);
			contentPane.add(getBalance);

			getUser = new JButton("Check User");
			getUser.setToolTipText("Checks and Prints the Name of the person with that balance");
			getUser.setSize(100, 50);
			getUser.setLocation(175 + 150 + 150, height - 100);
			getUser.addActionListener(this);
			getUser.setBackground(bg);
			getUser.setForeground(fg);
			getUser.setBorderPainted(false);
			getUser.addMouseListener(this);
			contentPane.add(getUser);

			close = new JButton("Close");
			close.setToolTipText("This Closes the window, but keeps the console open.");
			close.setSize(100, 50);
			close.setLocation(175 + 150 + 150 + 100, height - 100);
			close.addActionListener(this);
			close.setLayout(null);
			close.setBackground(bg);
			close.setForeground(fg);
			close.setBorderPainted(false);
			close.addMouseListener(this);
			contentPane.add(close);
		}

		setTitle("SignUp Form");
		setUndecorated(true);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(size);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(contentPane);
		main.updateFrame(this, getGraphics());

	}

	/**
	 * Test a text-to-double conversion
	 * 
	 * @param text
	 * @return rather the number is a viable monetary value
	 */
	public boolean test(String text) {
		try {
			if (Double.parseDouble(text) < Double.MAX_VALUE) {
				Double.parseDouble(text);
				return true;
			} else {
				main.print("Whooah, I'm sorry you make too much money we are going to have to refer you to another bank!\nThe Max balance we can confortably afford is " + moneyFormatter.format(Double.MAX_VALUE - 2) + "\nYou would have to be the richest person in the world");
				return false;
			}
		} catch (NumberFormatException e) {
			main.print("ERROR: Can't parse the text \"" + text + "\" as a double");
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean okBalance = false, okName = false;
		if (e.getSource().equals(list)) {
			main.print("Accounts: \n+-------------------------+");
			for (int i = 0; i < accounts.size(); i++) {
				main.print("+--Holder: " + accounts.get(i).getHolder() + "\n+-------- Balance of: " + moneyFormatter.format(accounts.get(i).getBalance()) + "\n+-------------------------+");
			}
		} else if (e.getSource().equals(getBalance)) {
			if (nameBox.getText() != initNameBox) {
				for (int i = 0; i < accounts.size(); i++) {
					if (accounts.get(i).getHolder().equalsIgnoreCase(nameBox.getText())) {
						main.print(accounts.get(i).getHolder() + " has a balance of " + moneyFormatter.format(accounts.get(i).getBalance()));
					}
				}
			} else {
				main.print("You need to type a username for this action!!!");
			}
		} else if (e.getSource().equals(getUser)) {
			if (balanceBox.getText() != initBalanceBox) {
				for (int i = 0; i < accounts.size(); i++) {
					if (accounts.get(i).getBalance() == Double.parseDouble(balanceBox.getText())) {
						main.print(accounts.get(i).getHolder() + " has a balance of " + accounts.get(i).getBalance() + ".  It's Creepy you know that!");
					}
				}
			} else {
				main.print("You need to type a username for this action!!!");
			}
		} else if (e.getSource().equals(confirm)) {
			if (nameBox.getText().equals(initNameBox)) {
				name = "";
				okName = false;
				main.print("You at least need to type a name");
			} else if (!nameBox.getText().equals("")) {
				name = nameBox.getText();
				okName = true;
			}
			if (balanceBox.getText().equals(initBalanceBox)) {
				balance = 0.00;
				okBalance = true;
				main.print("The Initial Deposit was empty so marking it as $0.00");
			} else if (!balanceBox.getText().equals("")) {
				balance = Double.parseDouble(balanceBox.getText());
				okBalance = true;
			} else if (balance >= Double.MAX_VALUE) {
				main.print("Whooah, I'm sorry you make too much money we are going to have to refer you to another bank!");
				okBalance = false;
			}
			if (okBalance && okName) {
				accounts.add(new SavingsAccount(balance, name));
				main.print("Account created for " + name + " with a balance of " + moneyFormatter.format(balance));
			}
		} else if (e.getSource().equals(close)) {
			SaveToFile();
			dispose();
		}
	}

	/**
	 * Saves file to the BIOS
	 */
	public void SaveToFile() {
		List<String> content = new ArrayList<String>();
		// loops through all current accounts to save.
		for (int i = 0; i < accounts.size(); i++) {
			main.print("Count = " + i);
			String holder = accounts.get(i).getHolder();
			content.add("Name" + i + ":" + holder + "\nBalance" + i + ":" + accounts.get(i).getBalance());
		}
		String finalContent = content.toString().replace("[", "").replace("]", "").replace(", ", "\n");
		main.print(finalContent);
		accounts.clear();
		try {
			io.TextWriter("bankinfo.dat", finalContent, "DataBase/", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads DataBase info from the BIOS
	 */
	public void ReadFromFile() {
		try {
			io.TextReader("bankinfo.dat", "Settings/DataBase/", "bank");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Registers Focus and rather the textboxes should be blank or have content
	 * in them on focus gained
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(nameBox)) {
			if (nameBox.getText().equals("Enter Your Name"))
				nameBox.setText("");
		} else if (e.getSource().equals(balanceBox)) {
			if (balanceBox.getText().equals("Enter Your Initial Deposit"))
				balanceBox.setText("");
		}

	}

	/**
	 * Registers Focus and rather the textboxes should be blank or have content
	 * in them on focus lost
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(nameBox)) {
			if (nameBox.getText().equals(""))
				nameBox.setText(initNameBox);
		} else if (e.getSource().equals(balanceBox)) {
			if (balanceBox.getText().equals("") || !test(balanceBox.getText()))
				balanceBox.setText(initBalanceBox);
		}
	}

	public String randomBarcode() {
		char[] abc = new Main().abc;
		Random r = new Random();
		return ("" + abc[r.nextInt(abc.length - 1)] + abc[r.nextInt(abc.length - 1)] + "-" + r.nextInt(50000) + "  " + abc[r.nextInt(abc.length - 1)] + abc[r.nextInt(abc.length - 1)] + "-" + r.nextInt(50000));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(stylelbl)) {
			stylelbl.setText(randomBarcode());
		}

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
		if (e.getSource().equals(confirm)) {
			confirm.setForeground(new Main().getTitleFg(confirm.getBackground()));
		} else if (e.getSource().equals(close)) {
			close.setForeground(new Main().getTitleFg(close.getBackground()));
		} else if (e.getSource().equals(getBalance)) {
			getBalance.setForeground(new Main().getTitleFg(getBalance.getBackground()));
		} else if (e.getSource().equals(getUser)) {
			getUser.setForeground(new Main().getTitleFg(getUser.getBackground()));
		} else if (e.getSource().equals(list)) {
			list.setForeground(new Main().getTitleFg(list.getBackground()));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource().equals(confirm)) {
			confirm.setForeground(new Main().getFg());
		} else if (e.getSource().equals(close)) {
			close.setForeground(new Main().getFg());
		} else if (e.getSource().equals(getBalance)) {
			getBalance.setForeground(new Main().getFg());
		} else if (e.getSource().equals(getUser)) {
			getUser.setForeground(new Main().getFg());
		} else if (e.getSource().equals(list)) {
			list.setForeground(new Main().getFg());
		}

	}

}
