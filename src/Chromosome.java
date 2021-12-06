import java.util.ArrayList;
import java.util.Collections;

import static java.lang.System.gc;

public class Chromosome {

	private int chromosomeSize = 0;
	private String chromosomeString = "";
	private String[] genes;
	private double chromosomeDistance = 0;
	private double fitnessValue = 0;
	private int[] localities;

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

	public int getChromosomeSize() {
		return chromosomeSize;
	}
	public String getChromosomeString() {
		return chromosomeString;
	}
	public String[] getGenes() {
		return this.genes;
	}
	public double getChromosomeDistance() {
		return chromosomeDistance;
	}

	public void setFitnessValue(String[] solutionChromosome) {
		ChromosomeFitness cf = new ChromosomeFitness (solutionChromosome);
		this.fitnessValue = cf.getChromosomeFitness(this);
	}

	public double getFitnessValue() {
		return fitnessValue;
	}

	/*
	Set a value to a specific gene (used during evolution)
	 */
	public void setGene(int geneIndex, String geneValue) {
		this.getGenes()[geneIndex] = geneValue;
	}

	/*
	Create a random chromosome
	 */
	public void createRandomChromosome() {
		int r = 0;	// Randomised gene
		int min = Utilities.getMinMax(null, localities, true, false)[0];
		int max = Utilities.getMinMax(null, localities, true, false)[1];
		String visited = "";	// Visited cities (already-generated genes)
		// Start the randomisation
		while (visited.split(",").length < (localities.length - 1)) {
			// Generate a random gene
			r = Utilities.getRandom(min, max);
			// Check if the randomly-generated gene already exists in this chromosome
			if (visited.length() > 0) {
				// Check if the randomly-generated gene already exists in this chromosome
				if (Utilities.convertArrayToIntArrayList
						(null, visited.split(","), false).indexOf(r) == -1)  {
					visited += String.valueOf(r) + ",";
					//this.genes[(visited.split(",").length)] = String.valueOf(r);
				}
			} else {
				visited += String.valueOf(r) + ",";
				//this.genes[(visited.split(",").length)] = String.valueOf(r);
			}
		}
		this.chromosomeString = localities[0] + "," + visited + localities[0];
		this.genes = this.chromosomeString.split(",");
		// Add the start and end locality to this gene
		this.genes[0] = String.valueOf(localities[0]);
		this.genes[(this.getGenes().length - 1)] = String.valueOf(localities[0]);

		// Calculate and store the distance of this chromosome
		this.chromosomeDistance =
				Utilities.getVoyageDistance(
						Utilities.convertArrayToIntArrayList(null, this.getGenes(),false));
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
