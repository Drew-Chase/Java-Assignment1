package tk.dccraft.excercies;

import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
/**
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Exercise3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your first and last name: ");
		String name = in.nextLine();
		System.out.print("Enter your first exam score (0-100): ");
		double score1 = 0, score2 = 0;
		String scoreS = in.nextLine();
		if (test(scoreS))
			score1 = Double.parseDouble(scoreS);
		else
			System.out.print("Please enter a proper number that is between 0-100");
		System.out.print("Enter your second exam score: ");
		String scoreS2 = in.nextLine();
		if (test(scoreS2))
			score2 = Double.parseDouble(scoreS2);
		else
			System.out.print("Please enter a proper number that is between 0-100");
		double finalScore = (score1+score2)/2;
		System.out.println(name + "'s exam average score is " + finalScore+"\n\n");
		
		 System.out.println("Maximum int value:  " + Integer.MAX_VALUE);
		 System.out.println("Max double value " + Double.MAX_VALUE);
		 System.out.println("Max byte value " + Byte.MAX_VALUE);
		 System.out.println("Max short value " + Short.MAX_VALUE);
		 System.out.println("Max float value " + Float.MAX_VALUE);
		 System.out.println("Max long value " + Long.MAX_VALUE);

	}

	public static boolean test(String test) {
		try {
			if (Double.parseDouble(test) >= 0 && Double.parseDouble(test) <= 100)
				return true;
			else
				return false;
		} catch (ParseException e) {
			return false;
		}
	}

}
