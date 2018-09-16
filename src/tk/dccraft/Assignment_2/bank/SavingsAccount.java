package tk.dccraft.Assignment_2.bank;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import tk.dccraft.init.Main;

/**
 * 
 * @author Drew Chase
 *
 */
public class SavingsAccount extends Main {

	List<String> username = new ArrayList<String>();
	List<Double> balance = new ArrayList<Double>();
	NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();

	public SavingsAccount(double balance, String name, double interestRate) {
		username.add(name);
		this.balance.add(balance);
		print("Added " + name + " with a balance of " + moneyFormatter.format(balance) + " with an interest rate of " + interestRate + "%");
		print(username.size());
	}

	public SavingsAccount(double balance, String name) {
		username.add(name);
		this.balance.add(balance);
		print("Added " + name + " with a balance of " + moneyFormatter.format(balance));
		print(username.size());
	}

	public SavingsAccount() {
		print(username.size());
	}

	/**
	 * 
	 * @param user
	 * @return Balance
	 */
	public double getBalance(String user) {
		String name = user.toLowerCase();
		for (int i = 0; i < balance.size(); i++) {
			if (username.get(i).equals(name)) {
				return balance.get(i);
			}
		}
		return 0.00;
	}

	/**
	 * 
	 * @param balance
	 * @return Owner Name
	 */
	public String getHolder(double balance) {
		for (int i = 0; i < username.size(); i++) {
			if (this.balance.get(i).equals(balance)) {
				return username.get(i);
			}
		}
		return "Account Holder Not Found";
	}

	public String listAccounts() {
		for (int i = 0; i < username.size(); i++) {
			String account = username.get(i) + " with a balance of " + balance.get(i);
			return account;
		}
		return "No Accounts Found";
	}

}
