import java.lang.Math;
import java.util.ArrayList;

public class Utilities {

	// ArrayList to store the coordinates
	public static ArrayList<City> cities = new ArrayList<>();

	// ArrayList to store the distance between each coordinates
	public static ArrayList<Distance> distances = new ArrayList<>();

	// Get the number of voyages the salesman must make to traverse all cities
	public int getNumOfVoyages() {
		return cities.size();
	}

	// Get the number of different permutations possible
	public int getNumOfPermutations() {
		int permutations = 0;
		for (int i = 1; i < cities.size(); i++) {
			permutations += (i * permutations);
		}
		return permutations;
	}

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
	Sets the distance between all the cities
	 */
	public static void setDistances() {
		for (City c1 : cities) {
			for (City c2 : cities) {
				if (c1 != c2) {
					distances.add(new Distance(c1.getIndex(), c2.getIndex(),
							getEuclideanDistance(c1.getX(), c1.getY(), c2.getX(), c2.getY())));
				}
			}
		}
	}

	/*
	Finds the shortest path between the cities list using brute force, using the Distances ArrayList.
	Path must start and end in the first element of the list.
	*/
	//public static int[] getShortestPath_bruteForce() {
	public static int getShortestPath_bruteForce() {
		try {
			int minDistance;

			/*
			// Loop through the Distances ArrayList for each of the elements departing from City 1
			for (int f = 0; f < distances.size(); f++) {
				if (distances.get(f).getFromCity() == 1) {
					// Loop through the other elements
				}
			}
			return 0;

			 */
		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

}
