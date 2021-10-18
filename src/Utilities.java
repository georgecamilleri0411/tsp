import java.lang.Math;
import java.util.ArrayList;

public class Utilities {

	/*
	Calculates the Euclidean distance between 2 points on a plane by using the Pythagorean theorem
	 */
	public static double getEuclideanDistance(int x1, int y1, int x2, int y2) {
		try {
			return Math.hypot(Math.abs(y1 - y2), Math.abs(x1 - x2));
		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

	/*
	Finds the shortest path between the cities list using brute force. Path must
	start and end in the first element of the list.

	public static int[] getShortestPath_bruteForce(ArrayList<City> cities) {
		try {
			for (City c1 : cities) {

			}
		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	*/
}
