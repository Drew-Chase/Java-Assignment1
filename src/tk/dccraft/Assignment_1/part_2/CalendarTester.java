package tk.dccraft.Assignment_1.part_2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Part 2 of Assignment 1
 * 
 * @author Drew Chase
 *
 */
@SuppressWarnings("all")
public class CalendarTester {

	// Initializing private variables
	private Calendar bDay, today;
	private DateFormat formatter, converter;
	private Date date = null;

	public CalendarTester() {
		formatter = new SimpleDateFormat("MM/dd/yyyy-HH:mm");
		converter = new SimpleDateFormat("MM/dd/yyyy-HH:mm aa");
		String output = "";

		today = Calendar.getInstance();

		bDay = Calendar.getInstance();
		bDay.set(bDay.YEAR, 2019);
		bDay.set(bDay.MONTH, bDay.NOVEMBER);
		bDay.set(bDay.HOUR, 10);
		bDay.set(bDay.DAY_OF_MONTH, 18);
		bDay.set(bDay.MINUTE, 55);
		try {
			date = formatter.parse(formatter.format(bDay.getTime()));
			output = converter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long difference = bDay.getTimeInMillis() - today.getTimeInMillis();
		long days = (((((difference /1000)/60)/60)/24));
		double year = days/365;

		print("Days to my 21st birth day: " + days + " and Years til' " + year);
	}

	public void print(Object message) {
		System.out.println(message);
	}

	public static void main(String[] args) {
		new CalendarTester();
	}

}
