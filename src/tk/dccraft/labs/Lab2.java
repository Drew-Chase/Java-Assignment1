package tk.dccraft.labs;

import java.text.NumberFormat;
import java.util.Random;

import tk.dccraft.init.Main;

/**
 * For Lab2 Create a Vending Machine like program. Sadly no GUI
 * 
 * @author Drew Chase
 *
 */
public class Lab2 extends Main {
	private int canAmount = 0, maxCanAmount = 25, cashAmount = 0;

	/**
	 * Fills the Vending Machine one by one with a half-second delay.
	 */
	public void fill() {
		for (int i = canAmount; i < maxCanAmount; i++) {
			canAmount++;
			print("Added one can to the vending machine\nVending Machine has " + canAmount + " in it currently");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		cashAmount = 0;
		print("Vending Machine is full and $" + getBills() + " has been collected");
	}

	/**
	 * Empties the Vending Machine by dispensing them one at a time with a
	 * random joke at the end.
	 */
	public void buyAll() {
		int currentCan = getCanCount();
		for (int i = 0; i < currentCan; i++) {
			dispense();
		}
		int ran = new Random().nextInt(3);
		if (ran == 0)
			print("You're a real selfish person");
		else if (ran == 1)
			print("Your parents would be ashamed.");
		else if (ran == 2)
			print("You're going to be fat from all that sugar");
		else if (ran == 3)
			print("There are starving people in Wakanda who could use that.\nMCU Joke :)");
	}

	/**
	 * Dispenses one can and adds one to the machines cash
	 */
	public void dispense() {
		if (getCanCount() > 0) {
			canAmount--;
			cashAmount++;
			print("Here you go one dollar for one can\n" + getCanCount() + " cans left");
		} else
			print("There aren't any cans left, sorry!");
	}

	/**
	 * 
	 * @return One Dollar Bills in Machine
	 */
	public int getBills() {
		return cashAmount;
	}

	/**
	 * 
	 * @return the Current amount of cans in the Machine
	 */
	public int getCanCount() {
		return canAmount;
	}

	/**
	 * Starts the Machine with canAmount
	 * 
	 * @param canAmount
	 */
	public Lab2(int canAmount) {
		this.canAmount = canAmount;
		double percent = (double) canAmount / maxCanAmount;
		NumberFormat per = NumberFormat.getPercentInstance();
		print("New Vending Machine with " + per.format(percent) + " of its total capacity");
	}

	/**
	 * Starts the Vending Machine with max
	 */
	public Lab2() {
		this.canAmount = maxCanAmount;
		print("New Vending Machine with 100% of its total capacity.\nIt's Full.");
	}
}