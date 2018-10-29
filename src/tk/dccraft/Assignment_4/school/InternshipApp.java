package tk.dccraft.Assignment_4.school;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * GUI App that allows conversing with the Students Object
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class InternshipApp extends Students {
	JFrame f;
	JPanel pane;
	JButton check, close;
	JTextField studentTF, creditsTF, gpaTF;
	char[] symbols = "0123456789`~!@#$%^&*()_-+=[{}]|\\'\";:/?.>,<*".toCharArray();
	String initStudentText = "Students Name", initGPAText = "Enter Your Current GPA Here", initCreditText = "Enter Your Current Credit Ammount";

	private double gpa, credit;

	/**
	 * Constructor
	 */
	public InternshipApp() {
		f = createWindow(new Dimension(800, 350), "InternshipApp");
		pane = createContentPane("Internship App", "Fantastic.ttf");

		studentTF = createTextBox(initStudentText, new Point(f.getWidth() / 3, f.getHeight() / 2 - 69 - 30 - 30), new Dimension(f.getWidth() / 3, 25));
		pane.add(studentTF);

		creditsTF = createTextBox(initCreditText, new Point(f.getWidth() / 3, f.getHeight() / 2 - 69 - 30), new Dimension(f.getWidth() / 3, 25));
		pane.add(creditsTF);

		gpaTF = createTextBox(initGPAText, new Point(f.getWidth() / 3, f.getHeight() / 2 - 69), new Dimension(f.getWidth() / 3, 25));
		pane.add(gpaTF);

		check = createButton("Check", new Point((f.getWidth() / 2) - 35, f.getHeight() - 100), new Dimension(69, 25));
		check.addActionListener(this);
		pane.add(check);

		close = createButton("Close", new Point((f.getWidth() / 2) + 35, f.getHeight() - 100), new Dimension(69, 25));
		close.addActionListener(this);
		pane.add(close);
		f.setContentPane(pane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(check)) {
			if (!(studentTF.getText().equals(initStudentText) || creditsTF.getText().equals(initCreditText) || gpaTF.getText().equals(initGPAText))) {
				Students s = new Students(studentTF.getText(), credit, gpa);
				print(gpa + " , " + credit);
				if (s.isEligibleForInternship())
					print("\n\nHello " + s.getName() + " We would like to Congratulate you on your " + s.getGPA() + " GPA\nand with the credits You've earned so far you are technically a " + s.getClassStatus() + "\nYou are currently Eligible for the Majority of Internships");
				else
					print("\n\nHello Student...\nWe are Sorry to Inform you that you do not meet the minimum\nrequirements to under take an internship.\nStudent Name:" + s.getName() + "\nStudent GPA " + s.getGPA() + "\nStudent Class Status " + s.getClassStatus());
			} else
				print("You've Got to type something in...");
		} else if (e.getSource().equals(close)) {
			f.dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(studentTF)) {
			if (studentTF.getText().equals(initStudentText)) {
				studentTF.setText("");
			}
		} else if (e.getSource().equals(creditsTF)) {
			if (creditsTF.getText().equals(initCreditText)) {
				creditsTF.setText("");
			}
		} else if (e.getSource().equals(gpaTF)) {
			if (gpaTF.getText().equals(initGPAText)) {
				gpaTF.setText("");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(studentTF)) {
			if (!isProperName(studentTF.getText()) || studentTF.getText().equals("")) {
				studentTF.setText(initStudentText);
			}
		} else if (e.getSource().equals(creditsTF)) {
			if (!isDouble(creditsTF.getText(), "credits") || creditsTF.getText().equals("")) {
				creditsTF.setText(initCreditText);
			}
		} else if (e.getSource().equals(gpaTF)) {
			if (!isDouble(gpaTF.getText(), "gpa") || gpaTF.getText().equals("")) {
				gpaTF.setText(initGPAText);
			}
		}
	}

	/**
	 * 
	 * @param s
	 * @return if it contains any symbols
	 */
	public boolean isProperName(String s) {
		for (int i = 0; i < symbols.length; i++) {
			if (s.contains(symbols[i] + "")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param s
	 * @param type
	 * @return whether its a double or not or gpa or credit
	 */
	public boolean isDouble(String s, String type) {
		if (!s.isEmpty()) {
			if (type.equalsIgnoreCase("gpa")) {
				try {
					gpa = Double.parseDouble(s);
					print(gpa);
					return isValidGPA(gpa);
				} catch (NumberFormatException | ParseException e) {
					print("Not a valid GPA");
					return false;
				}
			} else if (type.equalsIgnoreCase("credits")) {
				try {
					credit = Double.parseDouble(s);
					return isValidCredits(credit);
				} catch (NumberFormatException | ParseException e) {
					print("Not a valid number");
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param gpa
	 * @return is it a valid GPA
	 */
	public boolean isValidGPA(double gpa) {

		if (gpa > 4 || gpa < 0)
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @param credit
	 * @return is it a valid Credit Score
	 */
	public boolean isValidCredits(double credit) {
		if (credit > 100 || credit <= 0)
			return false;
		else
			return true;
	}

}
