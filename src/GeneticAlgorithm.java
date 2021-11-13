import java.util.ArrayList;
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
	Create the population using randomisation. This method will create random
	patterns of the locality numbers in between the start and end localities,
	which is always locality 1.
	One of the population (journeys) will be the Best Neighbour, since this is
	proven to return a good (if not the best) journey.
	*/
	private void createPopulation (int populationNumber) {
		try {
			// Re-initialise the existing population
			this.population = new String[populationNumber];


			int p = 0;	// counter
			String randomPop = "";	// Randomised population (pipe-delimited)

			// First element of population array will be the BeFS output
			this.population[p] = convertListToPipeDelimited(null
					, Utilities.solveTSP_GreedyBeFS(localities), false);

			while (p <= populationNumber) {
				// Randomise (slightly) the GreedyBeFS list until population number is reached
				p++;
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
	Transforms an Integer array into a pipe-delimited String
	*/
	private String convertListToPipeDelimited (int[] inputA, ArrayList<Integer> inputAL, boolean useArray) {
		String output = "";
		try {
			if ((inputA != null) && useArray) {
				for (int i : inputA) {
					output += i;
					if (i != (inputA[inputA.length - 1])) {
						output += "|";
					}
				}
			} else {
				if (inputAL != null) {
					for (int i : inputAL) {
						output += i;
						if (i != (inputAL.get(inputAL.size() - 1))) {
							output += "|";
						}
					}
				}
			}
			return output;
		} catch (Exception e) {
			System.out.println("GeneticAlgorithm.convertListToPipeDelimited - An error has occurred - " + e.getMessage());
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
