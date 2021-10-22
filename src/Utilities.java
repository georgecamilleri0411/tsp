import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class Utilities {

	// ArrayList to store the coordinates
	public static ArrayList<City> cities = new ArrayList<>();

	// ArrayList to store the distance between each coordinates
	public static ArrayList<Distance> distances = new ArrayList<>();

	// Array to store the different permutations
	public static int[][] permutations = new int[getNumOfPermutations(cities.size())][(getNumOfPermutations(cities.size()) + 1)];

	// Get the number of voyages the salesman must make to traverse all cities
	public int getNumOfVoyages() {
		return cities.size();
	}

	// Get the number of different permutations possible
	public static int getNumOfPermutations(int numOfCities) {
		int permutations = 1;
		for (int i = 1; i < (numOfCities - 1); i++) {
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
	public static int solveTSP_bruteForce() {
		try {
			int minDistance = -1;
			int[] totalDistance = new int[getNumOfPermutations(cities.size())];
			char[] voyage = new char[getNumOfPermutations(cities.size())];

			return 0;

			/*
			// Loop through the Distances ArrayList for each of the elements departing from City 1
			for (int f = 0; f < distances.size(); f++) {
				if (distances.get(f).getFromCity() == 1) {
					// Loop through the other elements - needs dev and test
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

	/*
	Generates all permutations of the integer array passed as argument
	 */
	public static void generatePermutations (int[] input) {

//		permutations = new int[getNumOfPermutations(cities.size())][(getNumOfPermutations(cities.size()) + 1)];
		permutations = new int[(getNumOfPermutations(cities.size()) + 1)][getNumOfPermutations(cities.size())];

		// Permutations counter
		int p = 0;

		// Initialise an integer array for the (number of cities - 1), since city 1 will always start
		int[] sequence = new int[cities.size() - 1];

		/*
		Add the values in their initial order in array input
		 */
		// Starting with the first city (i.e. starting point)
		permutations[p][0] = cities.get(0).getIndex();
		// Loop through the input array and populate the input array
		for (int ct = 0; ct < input.length; ct++) {
			permutations[p][(ct + 1)] = input[ct];
		}
		// Add the last element (city 1 again)
		permutations[p][(input.length + 1)] = cities.get(0).getIndex();

		// Use iterations to swap array elements
		int i = 0;
		while (i < sequence.length) {
			if (sequence[i] < i) {
				swap (input, i % 2 == 0 ? 0 : sequence[i], i);

				// Add the first element (city 1)
				permutations[p][0] = cities.get(0).getIndex();
				// Add the values in array input
				for (int ct = 0; ct < input.length; ct++) {
					permutations[p][(ct + 1)] = input[ct];
				}
				// Add the last element (city 1 again)
				permutations[p][(input.length + 1)] = cities.get(0).getIndex();

				// TEST START
				for (int x = 0; x <= (input.length + 1); x++) {
					System.out.println (p + "." + x + " : " + permutations[p][x]);
				}
				// TEST END

				sequence[i]++;
				i = 0;
				p = 0;
			} else {
				sequence[i] = 0;
				i++;
			}
			p++;
		}
	}

	/*
	Swaps two elements of the input array with each other
	 */
	private static void swap(int[] input, int e1, int e2) {
		int tmp = input[e1];
		input[e1] = input[e2];
		input[e2] = tmp;
	}

	/*
	Outputs the array to stdout in sequence
	 */
	private static void displaySequence(int[] input) {
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + " ");
		}
		System.out.println();
	}
}
