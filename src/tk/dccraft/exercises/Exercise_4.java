package tk.dccraft.exercises;

import java.util.Scanner;
/**
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Exercise_4 {

	public static void main(String[] args) {
		part1();
		part2();
	}

	public static void part1() {
		// purchase subtotal and constant tax rate (Maine)
		double subtotal = 59.99;
		double TAX_RATE = 0.055;

		// find the tax amount and purchase total
		double tax = subtotal * TAX_RATE;
		double total = subtotal + tax;

		// print the purchase total formatted to 2 places
		// with a $ in front.
		System.out.printf("Total is $%.2f \n", total); // FINISH ME

		// Compute a simple batting average
		int hits = 100; // DO NOT CHANGE ME
		int atBats = 372; // DO NOT CHANGE ME
		double average = (double) hits / atBats;

		// Print the batting average to 3 decimal places
		System.out.printf("Your batting average is %.3f \n", average); // FINISH
																		// ME
	}

	public static void part2() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter your phone number with area code: ");
		String line = in.nextLine();
		int phone = 0, areaCode = 0;
		try {
			String phoneString = line.replace("-", "").replace(" ", "").replace("(", "").replace(")", "");
			phone = Integer.parseInt(phoneString);
			areaCode = Integer.parseInt(phoneString.substring(0, 3));
			System.out.println("Huh your area code is "+ areaCode);
		} catch (Exception e) {
			System.out.println("Please enter a valid phone number (ex: (123)456-7890");
			part2();
		}
	}

}
