package tk.dccraft.Assignment_2.bank;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tk.dccraft.init.Main;

@SuppressWarnings("all")
public class SavingsAccountTester extends JFrame implements ActionListener, FocusListener {

	String title = "SignUp Form";
	JTextField nameBox, balanceBox;
	JPanel pane;
	JButton confirm, cancel, getBalance, getUser, list;
	int width, height;
	double balance;
	String name, initNameBox = "Enter Your Name", initBalanceBox = "Enter Your Initial Deposit";
	Main main;
	SavingsAccount coreyBank, sofiasBank, sa;

	public SavingsAccountTester() {
		sa = new SavingsAccount();
		coreyBank = new SavingsAccount(300, "Corey");
		sofiasBank = new SavingsAccount(2000, "Sofia", 2.5);
		main = new Main();
		width = (int) (getToolkit().getScreenSize().getWidth() / 2);
		height = (int) (getToolkit().getScreenSize().getHeight() / 2);
		Dimension size = new Dimension(width, height);

		pane = new JPanel();
		pane.setSize(width - 50, width - 50);
		pane.setBackground(Color.DARK_GRAY);
		pane.setForeground(Color.GRAY);

		nameBox = new JTextField(initNameBox);
		nameBox.setLocation((width / 2) + 50, height / 2);
		nameBox.setSize((width / 2) - 50, 25);
		nameBox.setVisible(true);
		nameBox.addActionListener(this);
		nameBox.addFocusListener(this);
		pane.add(nameBox);

		balanceBox = new JTextField(initBalanceBox);
		balanceBox.setLocation(width - 100, height / 2);
		balanceBox.setSize((width / 2) - 50, 25);
		balanceBox.setVisible(true);
		balanceBox.addActionListener(this);
		balanceBox.addFocusListener(this);
		pane.add(balanceBox);

		confirm = new JButton("Send");
		confirm.setToolTipText("This sends your information to the system");
		confirm.setSize(100, 50);
		confirm.setLocation(width / 3, height - 100);
		confirm.addActionListener(this);
		pane.add(confirm);

		list = new JButton("List All Accounts");
		list.setToolTipText("This gets information from the system");
		list.setSize(100, 50);
		list.setLocation(width / 3, height - 100);
		list.addActionListener(this);
		pane.add(list);

		getBalance = new JButton("Check Balance");
		getBalance.setToolTipText("Checks and Prints the Balance from a users profile");
		getBalance.setSize(100, 50);
		getBalance.setLocation(width / 3, height - 100);
		getBalance.addActionListener(this);
		pane.add(getBalance);

		getUser = new JButton("Check User");
		getUser.setToolTipText("Checks and Prints the Name of the person with that balance");
		getUser.setSize(100, 50);
		getUser.setLocation(width / 3, height - 100);
		getUser.addActionListener(this);
		pane.add(getUser);

		cancel = new JButton("Cancel");
		cancel.setToolTipText("This Closes the window, but keeps the console open.");
		cancel.setSize(100, 50);
		cancel.setLocation(width / 2, height - 100);
		cancel.addActionListener(this);
		pane.add(cancel);

		setTitle(title);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(size);
		setLocationRelativeTo(null);
		// setLayout(null);
		setContentPane(pane);

		new tk.dccraft.init.Main().updateFrame(this, getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean okBalance = false, okName = false;
		if (e.getSource().equals(list)) {
			main.print("Accounts: ");
			sa.listAccounts();
		} else if (e.getSource().equals(getBalance)) {
			if (nameBox.getText() != initNameBox) {
				main.print("Balance for " + nameBox.getText() + " is " + sa.getBalance(nameBox.getText()));
			} else {
				main.print("You need to type a username for this action!!!");
			}
		} else if (e.getSource().equals(getUser)) {
			if (balanceBox.getText() != initBalanceBox) {
				main.print("Account Holder is " + sa.getHolder(Double.parseDouble(balanceBox.getText())));
			} else {
				main.print("You need to type a username for this action!!!");
			}
		} else if (e.getSource().equals(confirm)) {
			if (nameBox.getText().equals(initNameBox)) {
				name = "";
				okName = false;
				main.print("You at least need to type a name");
			}
			if (!nameBox.getText().equals("")) {
				name = nameBox.getText();
				okName = true;
			}
			if (balanceBox.getText().equals(initBalanceBox)) {
				balance = 0.00;
				okBalance = true;
				main.print("The Initial Deposit was empty so marking it as $0.00");
			}
			if (!balanceBox.getText().equals("")) {
				balance = Double.parseDouble(balanceBox.getText());
				okBalance = true;
//				main.print("Account created for " + name + " with a balance of " + balance);
			}
			if (okBalance && okName) {
				sa = new SavingsAccount(balance, name);
				main.print("Account created for " + name + " with a balance of " + balance);
			}
		} else if (e.getSource().equals(cancel)) {
			this.dispose();
		}
	}

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

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(nameBox)) {
			if (nameBox.getText().equals(""))
				nameBox.setText("Enter Your Name");
		} else if (e.getSource().equals(balanceBox)) {
			if (balanceBox.getText().equals(""))
				balanceBox.setText("Enter Your Initial Deposit");
		}
	}

}
