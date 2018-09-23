package tk.dccraft.lab2;

import java.util.Random;

import tk.dccraft.init.Main;

public class VendingMachine extends Main {
	private int canAmount = 0, maxCanAmount = 25, cashAmount = 0;

	public void fill() {
		for (int i = canAmount; i < maxCanAmount; i++) {
			canAmount++;
			print("Added one can to the vending machine\nVending Machine has " + canAmount + " in it currently");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		cashAmount = 0;
		print("Vending Machine is full and $"+getBills()+" has been collected");
	}

	public void buyAll() {
		for (int i = 0; i < getCanCount(); i++) {
			dispense();
		}
		int ran = new Random().nextInt(3);
		if(ran ==0)
		print("You're a real selfish person");
		else if(ran == 1)
			print("Your parents would be ashamed.");
		else if(ran == 2)
			print("You're going to be fat from all that sugar");
		else if(ran == 3)
			print("There are starving people in Wakanda who could use that.\nMCU Joke :)");
	}

	public void dispense() {
		if (getCanCount() > 0) {
			canAmount--;
			cashAmount++;
			print("Here you go one dollar for one can\n" + getCanCount() + " cans left");
		} else
			print("There aren't any cans left, sorry!");
	}

	public int getBills() {
		return cashAmount;
	}

	public int getCanCount() {
		return canAmount;
	}

	public VendingMachine(int canAmount) {
		this.canAmount = canAmount;
	}
}