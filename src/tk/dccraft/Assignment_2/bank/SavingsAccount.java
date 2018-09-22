package tk.dccraft.Assignment_2.bank;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tk.dccraft.init.Main;

/**
 * 
 * @author Drew Chase
 *
 */
public class SavingsAccount extends Main {

	private String name;
	private double rate, balance;
	// private BigDecimal balance;
	private NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();

	public SavingsAccount(double balance, String name, double interestRate) {
		this.name = name;
		this.rate = interestRate;
		this.balance = balance;

		print("Added " + name + " with a balance of " + moneyFormatter.format(balance) + " with an interest rate of " + interestRate + "%");
	}

	public SavingsAccount(double balance, String name) {
		this.name = name;
		this.rate = 1.8;
		this.balance = balance;
		print("Added " + name + " with a balance of " + moneyFormatter.format(balance));
	}

	/**
	 * @param user
	 * @return Balance
	 */
	public double getBalance() {
		return balance;
	}

	public void deposit(double input) {
		balance += input;
		addInterest();
	}

	public void addInterest() {
		balance *= (rate / 100);
	}

	/**
	 * 
	 * @param balance
	 * @return Owner Name
	 */
	public String getHolder() {
		return this.name;
	}

}
