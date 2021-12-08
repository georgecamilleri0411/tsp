public class GeneticAlgorithm {

	/*
	Crossover and mutation settings
	 */
	private static final double mutationRate = 0.015;
	private static final int popGroupSize = 5;
	private static final boolean useElitism = true;

	/*
	Evolve the population
 	*/
	public static Population evolve(Population pop) {
		Population nextPopulation = new Population(pop.populationSize(), false);

		// If useElitism is set to True, and it will be, the fittest journey will not be discarded.
		int elitism = 0;
		if (useElitism) {
			nextPopulation.saveJourney(0, pop.getFittestJourney());
			elitism = 1;
		}

		// Crossover the population
		for (int c = elitism; c < nextPopulation.populationSize(); c++) {
			// Mate two journeys to create an offspring
			GA_Journey parent1 = candidateSelection(pop);
			GA_Journey parent2 = candidateSelection(pop);
			GA_Journey offspring = crossover(parent1, parent2);
			// Add the offspring to the next population
			nextPopulation.saveJourney(c, offspring);;
		}

		// Mutate this next population by swapping two cities randomly
		for (int e = elitism; e < nextPopulation.populationSize(); e++) {
			mutate(nextPopulation.getJourney(e));
		}

		return nextPopulation;

	}

	/*
	Run the crossover process by randomising a value between 0 and 1,
	comparing it to the crossoverRate (0.5) to choose which chromosome
	to use. The crossoverRate can be optimised by changing its value to
	create a bias in favour of the chromosome with the better fitness.
	 */
	private static GA_Journey crossover (GA_Journey parent1, GA_Journey parent2) {
		// Create the offspring journey
		GA_Journey offspring = new GA_Journey();

		// Set parent1 start and end positions
		int startPosition = (int) (Math.random() * parent1.journeyCities());
		int endPosition = (int) (Math.random() * parent1.journeyCities());

		// Do the crossover
		// Parent1
//		for (int p = 1; p < (offspring.journeyCities() - 1); p++) {
		for (int p = 0; p < (offspring.journeyCities()); p++) {
			if ((startPosition < endPosition) && (p > startPosition) && (p < endPosition)) {
//				if (!offspring.cityExists(parent1.getJourneyCity(p))) {
					offspring.placeCity(p, parent1.getJourneyCity(p));
//				}
			} else if (startPosition > endPosition) {
				if (!((p < startPosition) && (p > endPosition))) {
//					if (!offspring.cityExists(parent1.getJourneyCity(p))) {
						offspring.placeCity(p, parent1.getJourneyCity(p));
//					}
				}
			}
		}

		// Parent 2
		for (int p = 0; p < parent2.journeyCities(); p++) {
//		for (int p = 1; p < (parent2.journeyCities() - 1); p++) {
			// Check if the city exists in the offspring journey. If not, add it.
			if (!offspring.cityExists(parent2.getJourneyCity(p))) {
				// Find an unused element in the offspring journey
				for (int o = 0; o < offspring.journeyCities(); o++) {
					if (offspring.getJourneyCity(o) == null) {
						offspring.placeCity(o, parent2.getJourneyCity(p));
						break;
					}
				}
			}
		}
		return offspring;
	}

	/*
	Mutate a GAJourney by switching 2 genes randomly (stand/end genes are excluded)
	 */
	private static void mutate (GA_Journey GAJourney) {
//		for (int c1 = 0; c1 < GAJourney.journeyCities(); c1++) {
		for (int c1 = 1; c1 < (GAJourney.journeyCities() - 1); c1++) {
			if (Math.random() < mutationRate) {
				int c2 = (int) (Math.random() * GAJourney.journeyCities());
				while ((c2 != 0) && (c2 != c1) && (c2 < (GAJourney.journeyCities() - 1))) {
					c2 = (int) (Math.random() * GAJourney.journeyCities());
				}

				// Cities to mutate (swap)
				GA_City city1 = GAJourney.getJourneyCity(c1);
				GA_City city2 = GAJourney.getJourneyCity(c2);
				GAJourney.placeCity(c1, city2);
				GAJourney.placeCity(c2, city1);
			}
		}
	}

	/*
	Retrieve a candidate journey for crossover
	 */
	private static GA_Journey candidateSelection(Population pop) {
		// Create a population
		Population selection = new Population(popGroupSize, false);

		// Add random candidate journeys
		for (int g = 0; g < popGroupSize; g++) {
			int r = (int) (Math.random() * pop.populationSize());
			selection.saveJourney(g, pop.getJourney(r));
		}
		// Select the fittest (i.e. shortest) journey
		GA_Journey fittestJ = selection.getFittestJourney();
		return fittestJ;
	}

}
