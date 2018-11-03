package tk.dccraft.labs;

import java.util.Random;
import java.util.Scanner;

/**
 * Lab 3
 * 
 * @author Drew Chase
 *
 */
public class Lab3 {
	
	private final int HEADS_VALUE = 0, TAILS_VALUE = 1;	
	/**
	 * Constructor
	 */
	public Lab3() {
		Random r = new Random();
		Scanner in = new Scanner(System.in);
		String answer = "yes", text = "", flip = "";
		//Inits the Loop
		while (!answer.startsWith("n")) {
			int i = r.nextInt(2);
			//0 = heads and 1 = tails
			if (i == HEADS_VALUE) {
				flip = "HEADS";
			} else if (i == TAILS_VALUE) {
				flip = "TAILS";
			} else {
				System.out.println("Something went wrong!!!");
			}
			System.out.print("Please type either heads or tails: ");
			text = in.nextLine();

			if (text.toLowerCase().startsWith(flip.toLowerCase().substring(0, 1))) {
				System.out.println("Very Nice it was " + flip);
			} else {
				System.out.println("Oh sorry the answer was " + flip + " not " + text);
			}

			System.out.print("Type anything to continue or type \"n\" to stop: ");
			answer = in.nextLine();
		}
		//Closes the Stream
		in.close();
		System.exit(0);
	}

	public static void main(String[] args) {
		new Lab3();
	}

}
