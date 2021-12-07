public class GeneticAlgorithm {

	/*
	Crossover and mutation settings
	 */
	private static final double crossoverRate = 0.5;
	private static final double mutationRate = 0.015;
	private static final int popGroupSize = 5;
	private static final boolean useElitism = true;

	private static String[] genes;

	private static void setGenes() {
		genes = new String[(Utilities.cities.size())];
		for (int l = 0; l < Utilities.cities.size(); l++) {
			genes[l] = String.valueOf(Utilities.cities.get(l).getIndex());
		}
	}

	/*
	Run the crossover process by randomising a value between 0 and 1,
	comparing it to the crossoverRate (0.5) to choose which chromosome
	to use. The crossoverRate can be optimised by changing its value to
	create a bias in favour of the chromosome with the better fitness.
	 */
	private static Chromosome crossover (Chromosome chromosome1, Chromosome chromosome2) {
		// Populate the genes array with the list of cities;
		setGenes();

		Chromosome newChromosome = new Chromosome(genes);
		for (int g = 0; g < chromosome1.getChromosomeSize(); g++) {
			// Create the evolved Chromosome
			if (Math.random() <= crossoverRate) {
				newChromosome.setGene(g, chromosome1.getGenes()[g]);
			} else {
				newChromosome.setGene(g, chromosome2.getGenes()[g]);
			}
		}
		return newChromosome;
	}

	/*
	Mutate a chromosome by switching 2 genes randomly (stand/end genes are excluded)
	 */
	private static void mutate (Chromosome chromosome) {
		// Generate 2 random numbers between 1 and the (chromosome length -2)
		int gene1 = Utilities.getRandom (1, (chromosome.getChromosomeSize() - 1));
		int gene2 = 0;
		while (gene2 != 0 && gene2 != gene1) {
			gene2 = Utilities.getRandom (1, (chromosome.getChromosomeSize() - 1));
		}
		chromosome.setGene(gene1, chromosome.getGenes()[gene2]);
		chromosome.setGene(gene2, chromosome.getGenes()[gene1]);
	}

	/*
	Select chromosomes into a group and return the fittest one, for 'mating' and crossing over.
	 */
	private static Chromosome populationGroup (Population pop) {
		// Create the population
		Population newPop = new Population (popGroupSize, genes);
		for (int p = 0; p < popGroupSize; p++) {
			newPop.setChromosome(p, pop.getChromosome((int) Math.random() * pop.numberOfChromosomes()));
		}

		Chromosome fittestOne = newPop.getFittestChromosome();
		return newPop.getFittestChromosome();
	}

}
