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
	private int[] localities;
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
	patterns of the locality numbers in between the standing and end localities,
	which is always locality 1.
	*/
	private void createPopulation (int populationNumber) {
		try {
			// Re-initialise the existing population
			this.population = new String[populationNumber];

			int p = 0;	// counter
			String randomPop = "";	// Randomised population
			for (int i : localities) {
				randomPop += localities[i];
				if (i < (localities.length - 1)) {
					randomPop += "|";	// Separate locality number with a pipe
				}
			}
			while (p <= populationNumber) {

				p++;
			}

		} catch (Exception e) {
			System.out.println("GeneticAlgorithm.createPopulation - An error has occurred - " + e.getMessage());
			e.printStackTrace();
		} finally {
			gc();
		}
	}

	public void solveTSP_GA () {

	}

}
