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
	Retrieves the distance between two cities from the distances ArrayList
	*/
	public static double getDistance(int from, int to) {
		try {
			for (int d = 0; d < distances.size(); d++) {
				if ((distances.get(d).getFromCity() == from) && distances.get(d).getToCity() == to) {
					return distances.get(d).getDistance();
				}
				return 0;
			}
		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/*
	Generates all permutations of the integer array passed as argument
	 */
	public static void generatePermutations (int[] input) {

		permutations = new int[getNumOfPermutations(cities.size())][(cities.size() + 1)];

		// Initialise an integer array for the (number of cities - 1), since city 1 will always start
		int[] sequence = new int[cities.size() - 1];

		// Permutations counter
		int p = 0;

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

		// The first permutation (default sequence) is ready. Increment counter
		p++;

		// Use iterations to swap array elements
		int i = 0;
		while (i < sequence.length) {
			if (sequence[i] < i) {
				swap (input, i % 2 == 0 ? 0 : sequence[i], i);

				if (p < permutations.length) {
					// Add the first element (city 1)
					permutations[p][0] = cities.get(0).getIndex();
					// Add the values in permutations input
					for (int ct = 0; ct < input.length; ct++) {
						permutations[p][(ct + 1)] = input[ct];
					}
					// Add the last element (city 1 again)
					permutations[p][(input.length + 1)] = cities.get(0).getIndex();

				}

				// Increment the permutations counter
				p++;

				sequence[i]++;
				i = 0;
			} else {
				sequence[i] = 0;
				i++;
			}
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

	/*
	Finds the shortest path between the cities list using brute force, using the Permutations array.
	*/
	//public static int[] getShortestPath_bruteForce() {
	public static int solveTSP_bruteForce() {
		try {
			double minDistance = -1;
			int minVoyage = -1;
			double[] voyageDistance = new double[getNumOfPermutations(cities.size())];

			int from = 0;
			int to = 0;
			double cityDistance = 0;
			for (int p = 0; p < permutations.length; p++) {
				for (int v = 0; v < (cities.size() + 1); v++) {
					// First iteration - just get the 'from' city
					if (v == 0) {
						from = permutations[p][v];
						voyageDistance[p] = 0;
					} else {
						from = to;
						to = permutations[p][v];
					 	voyageDistance[p] += getDistance(from, to);
					}
				}
			}

			// Loop through the voyageDistance array to determine the shortest route
			for (int r = 0; r < voyageDistance.length; r++) {
				// First iteration, just store the voyage distance
				if (r == 0) {
					minDistance = voyageDistance[r];
					minVoyage = r;
				} else {
					if (voyageDistance[r] < minDistance) {
						minDistance = voyageDistance[r];
					}
				}
			}
			return minVoyage;

		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}

}
