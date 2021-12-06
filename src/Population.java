public class Population {

	private Chromosome[] chromosomes;

	public Population (int _populationSize, String[] _genes) {
		chromosomes = new Chromosome[_populationSize];
		for (int p = 0; p < _populationSize; p++) {
			chromosomes[p] = new Chromosome(_genes);
			chromosomes[p].createRandomChromosome();
		}
	}

	/*
	Returns the chromosome at the element index specified
	 */
	public Chromosome getChromosome(int idxElement) {
		return chromosomes[idxElement];
	}

	/*
	Returns the fittest chromosome from the chromosomes array
	 */
	public Chromosome getFittestChromosome() {
		Chromosome fittestChromosome = chromosomes[0];

		for (int f = 0; f < chromosomes.length; f++) {
			if (chromosomes[f].getFitnessValue() > fittestChromosome.getFitnessValue()) {
				fittestChromosome = chromosomes[f];
			}
		}
		return fittestChromosome;
	}

}
