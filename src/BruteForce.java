import java.math.BigInteger;
import java.util.ArrayList;

import static java.lang.System.gc;

public class BruteForce {

	private ArrayList<City> cityList = new ArrayList();
	// ArrayLists to store the distances between each city. 2nd ArrayList stores a 'hash' to aid performance
	public static ArrayList<Distance> distances = new ArrayList<>();
	public static ArrayList<String> distancesHash = new ArrayList();


	public BruteForce() {}

	public BruteForce (ArrayList<City> _cityList) {
		this.cityList = _cityList;
		setDistances();
	}

	/*
	Sets the distance between all the cities
 	*/
	public void setDistances() {
		try {
			for (City c1 : cityList) {
				for (City c2 : cityList) {
					if (c1 != c2) {
						distances.add(new Distance(c1.getIndex(), c2.getIndex(),
								Utilities.getEuclideanDistance(c1.getX(), c1.getY(), c2.getX(), c2.getY())));
						distancesHash.add (c1.getIndex() + "|" + c2.getIndex());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("BruteForce.setDistances - An error has occurred - " + e.getMessage());
			e.printStackTrace();
		} finally {
			gc();
		}
	}

	/*
	Retrieves the distance between two cities from the distances ArrayList
	*/
	public double getDistance(int from, int to) {
		try {
			/* This finds the element in distances using distancesHash.indexOf  */
			int result = distancesHash.indexOf(String.valueOf(from).trim() + "|" + String.valueOf(to).trim());
			if (result != -1) {
				return distances.get(result).getDistance();
			}
			return 0;

		} catch (Exception e) {
			System.out.println("BruteForce.getDistance - An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return 0;
		} finally {
			gc();
		}
	}

	/*
	Calculates the distance of a whole 'journey'
	 */
	public double getJourneyDistance(ArrayList<Integer> journey) {
		double output = 0;
		try {
			for (int v = 1; v < journey.size(); v++) {
				output += getDistance(journey.get(v - 1), journey.get(v));
			}
		} catch (Exception e) {
			System.out.println("BruteForce.getJourneyDistance - An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return 0;
		} finally {
			gc();
			return output;
		}
	}

	/*
	Swaps two elements of an Integer array with each other
	 */
	private void swap(int[] input, int e1, int e2) {
		int tmp = input[e1];
		input[e1] = input[e2];
		input[e2] = tmp;
	}

	public ArrayList<City> solveBruteForce () {
		ArrayList<City> solution = new ArrayList<>();

		for (BigInteger c = BigInteger.ZERO; c.compareTo((factorial(this.cityList.size()))) == -1; c.add(BigInteger.ONE)) {

		}

		return solution;
	}

	/*
	Calculate factorial
	 */
	private BigInteger factorial (int num) {
		BigInteger factorial = BigInteger.ONE;

		for (int f = 1; f <= num; f++) {
			factorial = factorial.multiply(BigInteger.valueOf(f));
		}
		return factorial;
	}

}
