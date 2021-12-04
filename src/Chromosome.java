import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.gc;

public class Chromosome {

	private int chromosomeSize = 0;
	private String chromosomeString = "";
	private String[] genes;
	private double chromosomeDistance = 0;
	private int fitnessValue = 0;
	private int[] localities;

	public int getChromosomeSize() {
		return chromosomeSize;
	}

	public String getChromosomeString() {
		return chromosomeString;
	}

	public String[] getGenes() {
		return genes;
	}

	public double getChromosomeDistance() {
		return chromosomeDistance;
	}

	public int getFitnessValue() {
		return fitnessValue;
	}

	/*
	Constructor
	 */
	public Chromosome (String[] _genes) {
		this.chromosomeSize = _genes.length;
		this.genes = new String[getChromosomeSize()];
		this.localities = new int[getChromosomeSize()];
		for (int g = 0; g < getChromosomeSize(); g++) {
			this.localities[g] = Integer.parseInt(_genes[g]);
		}

		for (int s = 0; s < _genes.length; s++) {
			this.chromosomeString += _genes[s];
			if (s < (_genes.length - 1)) {
				this.chromosomeString += ",";
			}
		}
		this.chromosomeDistance =
				Utilities.getVoyageDistance(
						Utilities.convertArrayToIntArrayList(null, _genes,false));
	}

	/*
	Create a random gene
	 */
	public void createRandomChromosome() {
		int r = 0;	// Randomised gene
		int min = getMinMax(null, localities, true, false)[0];
		int max = getMinMax(null, localities, true, false)[1];
		String visited = "";	// Visited cities (already-generated genes)
		// Start the randomisation
		while (visited.split(",").length < (localities.length - 1)) {
			// Generate a random gene
			r = getRandom(min, max);
			// Check if the randomly-generated gene already exists in this chromosome
			if (visited.length() > 0) {
				// Check if the randomly-generated gene already exists in this chromosome
				if (Utilities.convertArrayToIntArrayList
						(null, visited.split(","), false).indexOf(r) == -1)  {
					visited += String.valueOf(r) + ",";
					this.genes[(visited.split(",").length - 1)] = String.valueOf(r);
				}
			} else {
				visited += String.valueOf(r) + ",";
				this.genes[(visited.split(",").length - 1)] = String.valueOf(r);
			}
		}
		this.chromosomeString = localities[0] + "," + visited + localities[0];
		// Add the start and end locality to this gene
		this.genes[0] = String.valueOf(localities[0]);
		this.genes[(this.getGenes().length - 1)] = String.valueOf(localities[0]);

		// Calculate and store the distance of this chromosome
		this.chromosomeDistance =
				Utilities.getVoyageDistance(
						Utilities.convertArrayToIntArrayList(null, this.getGenes(),false));
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

}
