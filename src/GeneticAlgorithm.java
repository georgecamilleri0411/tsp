import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.lang.System.gc;

/*
Provides methods to solves the TSP using a Genetic Algorithm by:
1)	Creating a population: this could be a fixed number, but should preferably
	be a %age of the number of permutations.
	The randomised gene is stored in an array of String, and each new gene is compared
	to the existing values in the array to ensure no duplicates.
 */
public class GeneticAlgorithm {

	private long numOfPermutations = -1;
	private ArrayList<Integer> localities = new ArrayList();
	private String[] population;

	public GeneticAlgorithm() {}

	public GeneticAlgorithm(int[] localities) {
	}

	/*
	Get the number of different permutations possible
	 */
	public static long getNumOfPermutations(int numOfCities) {
		long permutations = 1;
		try {
			for (int i = 1; i < (numOfCities - 1); i++) {
				permutations += (i * permutations);
			}
		} catch (Exception e) {
			System.out.println("GeneticAlgorithm.getNumOfPermutations - An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return 0;
		} finally {
			gc();
			return permutations;
		}
	}

	/*
	Create the population. The first 2 parents will be the result of the
	Greedy Best Neighbour and its reverse. This method will also create random
	patterns of the locality numbers in between the start and end localities,
	which is always locality 1. Mutations will be made by swapping individual
	cities with each other, because of the added complexity of each city needing
	to feature only once.
	https://www.lalena.com/AI/TSP/
	*/
	private void createPopulation (int populationNumber) {
		try {
			// Re-initialise the existing population
			this.population = new String[populationNumber];

			int p = 0;	// Population counter

			// The first parent of population array will be the BeFS output
			this.population[p] = convertListToCommaDelimited(null
					, Utilities.solveTSP_GreedyBeFS(localities), false);
			p++;

			// Randomisation phase
			String randomPop = "";	// Randomised population (comma-delimited)
			ArrayList<Integer> visited = new ArrayList();
			// Clear the visited ArrayList
			for (int v : this.localities) {
				visited.add(0);
			}
			visited.remove(0);
			visited.trimToSize();
			while (p < populationNumber) {
				while (visited.indexOf(0) != -1) {
					// Randomise
				}
				p++;
			}

			// The last parent will be the reverse of the BeFS output
			if (p == populationNumber) {
				String[] q = this.population[(0)].trim().split((","));
				this.population[p] = "";
				for (int a = (q.length - 1); a >= 0; a--) {
					this.population[p] += q[a];
					if (a > 0) {
						this.population[p] += ",";
					}
				}
			}
		} catch (Exception e) {
			System.out.println("GeneticAlgorithm.createPopulation - An error has occurred - " + e.getMessage());
			e.printStackTrace();
		} finally {
			gc();
		}
	}

	/*
	Returns a random number within the specified range
	*/
	private int getRandom(int min, int max) {
		try {
			return (int) ((Math.random() * ((max + 1) - min)) + min);
		} catch (Exception e) {
			System.out.println("GeneticAlgorithm.getRandom - An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return -1;
		} finally {
			gc();
		}
	}

	/*
	Returns an array of Integer with the minimum and maximum values of an ArrayList
	*/
	private int[] getMinMax(ArrayList<Integer> input, boolean ignoreStartLocality) {
		int[] output = new int[2];
		try {
			ArrayList<Integer> s = input;
			// Sort the ArrayList
			Collections.sort(s);
			// Remove the first element if start locality needs to be ignored
			if (ignoreStartLocality) {
				s.remove(0);
				s.trimToSize();
			}
			output[0] = s.get(0);
			output[(output.length - 1)] = s.get((s.size() - 1));
		} catch (Exception e) {
			System.out.println("GeneticAlgorithm.getRandom - An error has occurred - " + e.getMessage());
			e.printStackTrace();
		} finally {
			gc();
			return output;
		}
	}

	/*
	Transforms an Integer array into a pipe-delimited String
	*/
	private String convertListToCommaDelimited(int[] inputA, ArrayList<Integer> inputAL, boolean useArray) {
		String output = "";
		try {
			if ((inputA != null) && useArray) {
				for (int i : inputA) {
					output += i;
					if (i != (inputA[inputA.length - 1])) {
						output += ",";
					}
				}
			} else {
				if (inputAL != null) {
					for (int i : inputAL) {
						output += i;
						if (i != (inputAL.get(inputAL.size() - 1))) {
							output += ",";
						}
					}
				}
			}
			return output;
		} catch (Exception e) {
			System.out.println("GeneticAlgorithm.convertListToCommaDelimited - An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return "";
		} finally {
			gc();
		}
	}

	/*
	Solves the TSP using a Genetic Algorithm, by starting with a population of
	journeys and evolving them into better (shorter) ones.
	 */
	public void solveTSP_GA () {

	}

}
