import java.util.ArrayList;
import java.util.Collections;
import static java.lang.System.gc;

/*
Provides methods to solves the TSP using a Genetic Algorithm by:
1)	Creating a population: this could be a fixed number, but should preferably
	be a %age of the number of permutations.
	The randomised gene is stored in an array of String, and each new gene is compared
	to the existing values in the array to ensure no duplicates.
 */
public class GeneticAlgorithm_old {

	final int defaultPopulationSize = 32;
	private double[] populationDistances = new double[defaultPopulationSize];
	private double fittestGeneDistance = 0;
	private int fittestGene = -1;
	//private long numOfPermutations = -1;
	private int[] localities;
	private String[] population;

	public GeneticAlgorithm_old() {}

	public GeneticAlgorithm_old(int[] cities) {
		this.localities = new int[(cities.length)];
		for (int c = 0; c < cities.length; c++) {
			this.localities[c] = cities[c];
		}
		createInitialPopulation(defaultPopulationSize, true);

		// TEST
		for (int x = 0; x < this.population.length; x++) {
			System.out.println (this.population[x]);
		}
		System.out.println ("Fittest gene: " + this.population[this.fittestGene]);
		System.out.println ("Best distance: " + this.fittestGeneDistance);

		// END TEST
	}

	private void checkFitness(double populationDistance, int p) {
		if ((this.fittestGeneDistance == 0) || (populationDistance < this.fittestGeneDistance)) {
			this.fittestGene = p;
			this.fittestGeneDistance = populationDistance;
		}
	}

	/*
	Create the population. The first and last parents will be the result of the
	Greedy Best Neighbour and its reverse. This method will also create random
	patterns of the locality numbers in between the start and end localities,
	which is always locality 1. The data will be stored in comma-delimited string
	to enable faster searching using contains() or indexOf().
	Mutations will then be made by swapping individual cities with each other,
	because of the added complexity of each city needing
	to feature only once.
	https://www.lalena.com/AI/TSP/
	*/
	private void createInitialPopulation(int populationNumber, boolean noDuplicates) {
		try {
			// Check that the populationNumber does not exceed the number of permutations
			// If it does, the default number of permutations will be used.
			if ((populationNumber > Utilities.getNumOfPermutations2(Utilities.cities.size()))
					|| populationNumber > (Integer.MAX_VALUE)) {
				//populationNumber = (int) Utilities.getNumOfPermutations2(Utilities.cities.size());
				populationNumber = this.defaultPopulationSize;
			}

			// Re-initialise the existing population
			this.population = new String[populationNumber];

			int p = 0;	// Population counter

			// The first parent of population array will be the BeFS output
			this.population[p] = convertListToCommaDelimited(null
					, Utilities.solveTSP_GreedyBeFS(Utilities.convertArrayToIntArrayList(localities, null, true)), false);

			// Calculate and store the distance of this gene
			this.populationDistances[p] =
					Utilities.getVoyageDistance(
							Utilities.convertArrayToIntArrayList(null, this.population[p].split(","),false));
			// Check the fitness of this gene
			this.checkFitness(this.populationDistances[p], p);

			p++;

			// Randomisation phase
			int r = 0;
			int min = getMinMax(null, localities, true, false)[0];
			int max = getMinMax(null, localities, true, false)[1];
			String visited = "";	// Visited population (comma-delimited)
			// Start the randomisation
			while (p < (populationNumber - 1)) {
				while (visited.split(",").length < (localities.length - 1)) {
					// Randomise
					r = getRandom(min, max);
					if (visited.length() > 0) {
						if (Utilities.convertArrayToIntArrayList
								(null, visited.split(","), false).indexOf(r) == -1)  {
							visited += String.valueOf(r) + ",";
						}
					} else {
						visited += String.valueOf(r) + ",";
					}
					//r = getRandom(min, max);
				}
				visited = localities[0] + "," + visited + localities[0];
				// If required, ensure that the generated gene has not been already added to the population
				if ((!noDuplicates) || (noDuplicates && Utilities.convertArrayToStringArrayList(null, this.population
						, false).indexOf(visited) == -1)) {
					// Add the start and end locality to this gene
					this.population[p] = visited;
					visited = "";

					// Calculate and store the distance of this gene
					this.populationDistances[p] =
							Utilities.getVoyageDistance(
									Utilities.convertArrayToIntArrayList(null, this.population[p].split(","),false));
					// Check the fitness of this gene
					this.checkFitness(this.populationDistances[p], p);

					p++;
				} else {
					visited = "";
				}
			}

			// The last parent will be the reverse of the BeFS output
			if (p == (populationNumber - 1)) {
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
			System.out.println("GeneticAlgorithm_old.createPopulation - An error has occurred - " + e.getMessage());
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
			System.out.println("GeneticAlgorithm_old.getRandom - An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return -1;
		} finally {
			gc();
		}
	}

	/*
	Returns an array of Integer with 2 elements containing minimum and maximum values of an ArrayList or an Array
	*/
	private int[] getMinMax(ArrayList<Integer> inputAL, int[] inputA, boolean ignoreStartLocality, boolean useArrayList) {
		int[] output = new int[2];
		try {
			ArrayList<Integer> s = new ArrayList();
			if (useArrayList) {
				s = inputAL;
			} else {
				for (int e = 0; e < inputA.length; e++) {
					s.add(inputA[e]);
				}
			}

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
			System.out.println("GeneticAlgorithm_old.getRandom - An error has occurred - " + e.getMessage());
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
				for (int i = 0; i < inputA.length; i++) {
					output += inputA[i];
					if (i != (inputA[inputA.length - 1])) {
						output += ",";
					}
				}
			} else {
				if (inputAL != null) {
					for (int i = 0; i < inputAL.size(); i++) {
						output += inputAL.get(i).toString();
						if (i != (inputAL.size() - 1)) {
							output += ",";
						}
					}
				}
			}
			return output;
		} catch (Exception e) {
			System.out.println("GeneticAlgorithm_old.convertListToCommaDelimited - An error has occurred - " + e.getMessage());
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
	public int[] solveTSP_GA () {
		int[] output = new int[(this.localities.length)];
		return output;
	}

}
