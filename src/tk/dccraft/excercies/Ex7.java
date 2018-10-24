package tk.dccraft.excercies;

import java.util.Scanner;

@SuppressWarnings("all")
public class Ex7 {

	public Ex7() {
		whileCounter();
		forCounter();
		input();
	}

	public void input() {
		Scanner in = new Scanner(System.in);
		System.out.print("Type any string or quit to quit: ");
		String text = in.nextLine();
		while (!text.equalsIgnoreCase("quit")) {

			char[] c = text.toCharArray();
			for (char ch : c) {
				if (ch == 'g') {
					System.out.println("FOUND THE G");
				}
			}

			// if(text.toLowerCase().contains("g")){
			// System.out.println("FOUND THE G");
			// }

			System.out.print("Type any string or quit to quit: ");
			text = in.nextLine();
		}
	}

	public void forCounter() {
		for (int i = 20; i >= 5; i--) {
			System.out.println("Index: " + i);
		}
		for (int i = 0; i <= 30; i += 3) {
			System.out.println("Index: " + i);
		}
	}

	public void whileCounter() {
		int i = 5;
		while (i <= 20) {
			System.out.println("Index: " + i);
			i++;
		}
		i = 70;
		while (i >= 0) {
			System.out.println("Index: " + i);
			i -= 7;
		}
	}

	public static void main(String[] args) {
		new Ex7();
	}

}
