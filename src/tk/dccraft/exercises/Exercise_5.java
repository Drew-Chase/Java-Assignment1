package tk.dccraft.exercises;

import java.util.Random;
import java.util.Scanner;

public class Exercise_5 {

	int x = 0;

	public Exercise_5() {
		Random ran = new Random();
		Scanner in = new Scanner(System.in);
		String question = "yes";
		while (question.startsWith("y")) {
			int num = ran.nextInt(9) + 1;
			System.out.print("Please enter a guess (1-10): ");
			String guess = in.nextLine();

			if (test(guess) && guess.equals("" + num)) {
				System.out.println("You are completely correct!!!");
			} else
				System.out.println("Sorry the number was " + num + " not " + guess);
			System.out.print("Wanna play again (y|n): ");
			question = in.nextLine();
		}
		in.close();

	}

	public boolean test(String message) {

		try {
			x = Integer.parseInt(message);
			if (x >= 1 || x <= 10)
				return true;
			else
				return false;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	public static void main(String[] args) {
		new Exercise_5();
	}

}
