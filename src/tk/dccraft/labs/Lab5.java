package tk.dccraft.labs;

import java.util.ArrayList;

import tk.dccraft.init.Main;

public class Lab5 extends Main {

	public Lab5() {
		parallelArrays();
	}

	public void parallelArrays() {
		String[] movieTitles = new String[] { "There Will Be Blood", "Amadeus", "This Is Spinal Tap", "Dude, Where's My Car?", "The Princess Bride" };
		double[] movieRatings = new double[] { 8.1, 8.3, 8.0, 5.5, 8.1 };
		String[] finalString = new String[movieTitles.length];

		print("", 2);
		for (int i = 0; i < movieTitles.length; i++) {
			finalString[i] = movieTitles[i] + " has an IMDb rating of " + movieRatings[i];
			print(finalString[i]);
		}
		double average = 0.0, sum = 0.0;
		int count = 0;
		for (count = 0; count < movieRatings.length; count++) {
			sum += movieRatings[count];
		}

		average = sum / count;

		print("The Average Movie Rating is " + average + "/10");

	}

}

class MovieTester {
	ArrayList<Movie> movies = new ArrayList<Movie>();

	public MovieTester() {
		String[] movieTitles = new String[] { "There Will Be Blood", "Amadeus", "This Is Spinal Tap", "Dude, Where's My Car?", "The Princess Bride" };
		double[] movieRatings = new double[] { 8.1, 8.3, 8.0, 5.5, 8.1 };
		for (int i = 0; i < movieTitles.length || i < movieRatings.length; i++) {
			movies.add(new Movie(movieTitles[i], movieRatings[i]));
		}
		
		for(Movie m : movies){
			m.getRatings();
		}
	}

}

class Movie {

	String name;
	double rating;

	public Movie(String name, double rating) {
		this.name = name;
		this.rating = rating;
	}
	
	public double getRatings(){
		return rating;
	}

}
