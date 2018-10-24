package tk.dccraft.lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab4 {

	public Lab4() {
		String text = "", func = "";
		while (!(text.equalsIgnoreCase("no") || text.toLowerCase().startsWith("n"))) {
			Scanner in = new Scanner(System.in);
			System.out.print("Type p1 (Part 1) or p2 (Part 2): ");
			func = in.nextLine();
			if (func.equalsIgnoreCase("p1") || func.equalsIgnoreCase("part 1") || (func.contains("1") && !func.contains("2")))
				getInput();
			else if (func.equalsIgnoreCase("p2") || func.equalsIgnoreCase("part 2") || (func.contains("2") && !func.contains("1")))
				nestedLoops();
			else {
				System.out.println("Incorrect value entered.");
				continue;
			}
			System.out.print("Please press enter to continue or type (n | no) to stop: ");
			text = in.nextLine();
		}
	}

	public void nestedLoops() {
		Scanner sc = new Scanner(System.in);
		int no = 0;
		System.out.println("Enter a number: ");
		no = sc.nextInt();
		for (int i = 0; i < 10; i++) {
			System.out.println(no + " * " + (i + 1) + " = " + (no * (i + 1)));
		}
	}

	public void getInput() {
		Scanner in = new Scanner(System.in);
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int count = 0;
		double sum = 0;
		double average = 0;
		int number = 0;
		System.out.print("Type a series of number and end it with \"0\": ");
		try {
			number = in.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Example: \"1 2 3 4 5 6 7 8 9 0\"\n");
			getInput();
		}
		while (number != 0) {
			if (number > max)
				max = number;
			if (number < min)
				min = number;

			sum += number;
			count++;

			number = in.nextInt();
		}
		average = sum / count;
		System.out.print("Max is: " + max + "\nMin is: " + min + "\nAverage is: " + average + "\nSum is: " + sum + "\n");

	}

	public static void main(String[] args) {
		new Lab4();
	}

}
