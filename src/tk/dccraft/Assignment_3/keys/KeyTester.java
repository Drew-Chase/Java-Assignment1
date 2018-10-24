package tk.dccraft.Assignment_3.keys;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tk.dccraft.init.Main;

@SuppressWarnings("all")
public class KeyTester extends Main implements FocusListener {

	private JFrame f;
	private JPanel pane;
	private JTextField keyField;
	private String initKey = "Please Type a PassPhrase to Test", title = "Test YO' Passwords!";
	private JButton confirm, close;
	private JLabel titlelbl;
	private int width = 800, height = 250, padding = 10;
	private Dimension size = new Dimension(width, height);

	private final int KEY_BREAK_RATE = 100000000;// 15,050,850,210 Bits per
													// Second as of 2018 but
													// thats too bit :(

	public KeyTester() {
		pane = new JPanel();
		pane.setLayout(null);
		pane.setSize(size);
		pane.setBackground(getBg());
		pane.setForeground(getFg());

		titlelbl = new JLabel(title);
		titlelbl.setFont(new Font(initFonts("HACKED").getFontName(), Font.PLAIN, 32));
		titlelbl.setForeground(getTitleFg());
		titlelbl.setSize(title.length() * titlelbl.getFont().getSize(), titlelbl.getFont().getSize());
		titlelbl.setLocation(padding, padding);
		pane.add(titlelbl);

		keyField = new JTextField(initKey);
		keyField.setEnabled(true);
		keyField.addFocusListener(this);
		keyField.setLayout(null);
		keyField.setSize(size.width / 3, 25);
		keyField.setLocation((size.width / 2) - (keyField.getWidth() / 2), size.height / 2);
		keyField.setToolTipText(initKey);
		pane.add(keyField);

		confirm = new JButton("Test");
		confirm.setLayout(null);
		confirm.addActionListener(this);
		confirm.setBackground(getBg());
		confirm.setForeground(getFg());
		confirm.setSize((confirm.getText().length() + 1) * confirm.getFont().getSize(), 25);
		confirm.setLocation((size.width / 2) - (confirm.getWidth() / 2) - 75, (size.height - confirm.getHeight()) - padding);
		confirm.setToolTipText("Sends Your PassPhrase to the workers to test");
		confirm.setEnabled(false);
		pane.add(confirm);

		close = new JButton("Save/Close");
		close.setLayout(null);
		close.setLocation((confirm.getLocation().x + confirm.getWidth()) + padding, confirm.getLocation().y);
		close.addActionListener(this);
		close.setBackground(getBg());
		close.setForeground(getFg());
		close.setToolTipText("Saves the Results and Closes the Window.");
		close.setSize(close.getText().length() * close.getFont().getSize(), 25);
		close.requestFocus();
		pane.add(close);
		keyField.transferFocusDownCycle();

		f = new JFrame();
		f.setSize(size);
		f.setUndecorated(true);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.addFocusListener(this);
		f.setLocationRelativeTo(null);
		f.setContentPane(pane);
	}

	String newPass = "";
	String chars = "0123456789aABbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyzZ";
	char[] symbols = "~!@#$%^&*()_-./?,:;\"\'\\[]{}|+=".toCharArray();

	public boolean isSymbol() {
		boolean answer = false;
		int i = 0;
		while (!answer && i < symbols.length) {
			if (keyField.getText().contains(symbols[i] + "")) {
				answer = true;
			}
			i++;
		}
		return answer;
	}

	public void Start() {
		String password = keyField.getText();
		long start = System.currentTimeMillis();
		crack(password, isSymbol());
		long end = System.currentTimeMillis();
		long milliSecs = TimeUnit.MILLISECONDS.toSeconds(end - start);
		;
		long secs = milliSecs / 1000;
		long mins = secs / 60;
		long hours = mins / 60;
		long days = hours / 24;
		long years = days / 365;
		days -= (years * 365);
		hours -= (days * 24);
		mins -= (hours * 60);
		secs -= (mins * 60);
		milliSecs -= (secs * 1000);
		print("The password is: " + newPass);
		if (years > 0) {
			if (years == 1) {
				print("it took\n" + years + "year\n" + days + " days\n" + hours + " hours\n" + mins + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			} else {
				print("it took\n" + years + "years\n" + days + " days\n" + hours + " hours\n" + mins + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			}
		} else if (days > 0) {
			if (days == 1) {
				print("it took\n" + days + " day\n" + hours + " hours\n" + mins + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			} else {
				print("it took\n" + days + " days\n" + hours + " hours\n" + mins + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			}
		} else if (hours > 0) {
			if (hours == 1) {
				print("it took\n" + hours + " hour\n" + mins + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			} else {
				print("it took\n" + hours + " hours\n" + mins + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			}
		} else if (mins > 0) {
			if (mins == 1) {
				print("it took\n" + mins + " min\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			} else {
				print("it took\n" + mins + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			}
		} else if (secs > 0) {
			if (secs == 1) {
				print("it took\n" + secs + " sec\n" + milliSecs + " milliseconds\nto find the password");
			} else {
				print("it took\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
			}
		} else {
			print("it took\n" + milliSecs + " milliseconds\nto find the password");
		}

		f.dispose();
	}

	private void crack(String password, boolean decideSymb) {
		if (decideSymb) {
			chars += symbols.toString();
		}
		for (int length = 2; length <= 15; length++) {
			newPass = "";
			for (int n = 0; n < length; n++) {
				newPass += "0";
			}
			int lastInd = length - 1;
			while (!newPass.equals(password)) {
				String end = "";
				for (int n = 0; n < newPass.length(); n++) {
					end += "Z";
				}
				if (newPass.equals(end)) {
					break;
				}
				int indInChars = chars.indexOf(newPass.charAt(lastInd));
				if (indInChars < chars.length() && indInChars >= 0) {
					boolean t = true;
					int howManyZs = 0;
					while (t) {
						if (newPass.charAt(newPass.length() - 1 - howManyZs) == 'Z') {
							howManyZs++;
						} else {
							t = false;
						}
					}
					String reset0s = "";
					for (int l = 0; l < howManyZs; l++) {
						reset0s += "0";
					}
					if (lastInd < newPass.length() - 1 && lastInd > 0) {
						lastInd--;
						indInChars = chars.indexOf(newPass.charAt(lastInd)) + 1;
						newPass = newPass.substring(0, lastInd) + chars.charAt(indInChars) + newPass.substring(lastInd + 1);
					} else if (newPass.length() - howManyZs == 1) {
						newPass = chars.substring(chars.indexOf(newPass.charAt(0)) + 1, chars.indexOf(newPass.charAt(0)) + 2) + reset0s;
					} else if (newPass.length() - howManyZs > 1 && howManyZs != 0) {
						newPass = newPass.substring(0, newPass.length() - 1 - howManyZs) + chars.substring(chars.indexOf(newPass.charAt(newPass.length() - 1 - howManyZs)) + 1, chars.indexOf(newPass.charAt(newPass.length() - 1 - howManyZs)) + 2) + reset0s;
					} else {
						indInChars = chars.indexOf(newPass.charAt(lastInd)) + 1;
						newPass = newPass.substring(0, lastInd) + chars.charAt(indInChars);
					}
					print(newPass);
				}
			}
			if (newPass.equals(password)) {
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource().equals(confirm)){
			Start();
		}else if(e.getSource().equals(close)){
			f.dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(keyField)) {
			if (keyField.getText().equalsIgnoreCase(initKey))
				keyField.setText("");
		} else if (e.getSource().equals(f)) {
			// keyField.lostFocus(e.event, (Object)f);
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(keyField)) {
			if (keyField.getText().equals(""))
				keyField.setText(initKey);
			else
				confirm.setEnabled(true);
		}
	}

}
