package tk.dccraft.lab2;

import tk.dccraft.init.Main;

public class VendingMachine extends Main{
	private int canAmount = 0, maxCanAmount = 25, cashAmount = 50;
	
	public void fill(){
		for(int i = canAmount; i < maxCanAmount; i++){
			canAmount++;
			print("Added one can to the vending machine\nVending Machine has " + canAmount+ " in it currently");
		}
		print("Vending Machine is full");
	}
	
	public void dispense(){
		canAmount--;
	}
	
	public int getBills(){
		return cashAmount;
	}
	
	public int getCanCount(){
		return canAmount;
	}
	
	public VendingMachine(int canAmount){
		this.canAmount = canAmount;
	}
}