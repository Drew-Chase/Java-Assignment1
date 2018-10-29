package tk.dccraft.Assignment_4.school;

import tk.dccraft.init.CustomWindow;
/**
 * Students Object / Shell
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class Students extends CustomWindow {

	private String name;
	private double credits, gpa;
 
	/**
	 * Used to Get rid of Super Class Errors
	 */
	public Students() {
	}

	/**
	 * Main Constructor
	 * @param name
	 * @param credits
	 * @param gpa
	 */
	public Students(String name, double credits, double gpa) {
		setName(name);
		setGPA(gpa);
		setCredits(credits);
	}

	public String getName() {
		return name;
	}
 /**
  * Sets the name
  * @param name
  */
	public void setName(String name) {
		this.name = name;
	}
/**
 * 
 * @return Credits
 */
	public double getCredits() {
		return credits;
	}

	/**
	 * Sets the Credits
	 * @param credits
	 */
	public void setCredits(double credits) {
		this.credits = credits;
	}

	/**
	 * 
	 * @return GPA
	 */
	public double getGPA() {
		return gpa;
	}

	/**
	 * 
	 * @param gpa
	 * @return Whether its a valid GPA
	 */
	public boolean setGPA(double gpa) {
		if (gpa > 4 || gpa < 0) {
			print("Sorry not a valid GPA");
			return false;
		} else {
			this.gpa = gpa;
			return true;
		}
	}

	/**
	 * 
	 * @return Text Based Grade Level
	 */
	public String getClassStatus() {
		int c = (getCredits() > 0 && getCredits() < 29) ? 0 : (getCredits() > 30 && getCredits() < 59) ? 1 : (getCredits() > 60 && getCredits() < 89) ? 2 : 3;
		
		switch (c) {
		case 0:
			return "First-Year";
		case 1:
			return "Softmore";
		case 2:
			return "Junior";
		case 3:
			return "Senior";
		default:
			return "Graduated";
		}
	}

	public boolean isEligibleForInternship() {
		if (!getClassStatus().equals("First-Year") && getGPA() >= 1.7)
			return true;
		else
			return false;
	}

}
