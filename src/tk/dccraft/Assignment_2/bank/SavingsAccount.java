package tk.dccraft.Assignment_2.bank;

import java.text.NumberFormat;

import tk.dccraft.init.Main;

/**
 * The Core Savings Account Shell
 * 
 * @author Drew Chase
 *
 */
public class SavingsAccount extends Main {

	private String name;
	private double rate, balance;
	private NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();

	/**
	 * Initializes with custom interest rate
	 * 
	 * @param balance
	 * @param name
	 * @param interestRate
	 */
	public SavingsAccount(double balance, String name, double interestRate) {
		this.name = name;
		this.rate = interestRate;
		this.balance = balance;

		print("Added " + name + " with a balance of " + moneyFormatter.format(balance) + " with an interest rate of " + interestRate + "%");
	}

	/**
	 * Initializes with default interest rate (1.8%)
	 * 
	 * @param balance
	 * @param name
	 */
	public SavingsAccount(double balance, String name) {
		this.name = name;
		this.rate = 1.8;
		this.balance = balance;
		print("Added " + name + " with a balance of " + moneyFormatter.format(balance));
	}

	/**
	 * Gets the Owners balance
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Deposits Input to balance
	 * 
	 * @param input
	 */
	public void deposit(double input) {
		balance += input;
		addInterest();
	}

	/**
	 * multiplies balance with interest rate
	 */
	public void addInterest() {
		balance *= (rate / 100);
	}

/**
 * gets the Name of the account holder
 * @return Owners Name
 */
	public String getHolder() {
		return this.name;
	}

}
