public class Population {

	private GA_Journey[] journeys;

	public Population (int _populationSize, boolean createNew) {
		journeys = new GA_Journey[_populationSize];
		// Create a new population of journeys?
		if (createNew) {
			for (int p = 0; p < _populationSize; p++) {
				GA_Journey newJourney = new GA_Journey();
				newJourney.generateRandomJourney();
				saveJourney (p, newJourney);
			}
		}
	}

	/*
	Save the journey specified at the element index specified.
	 */
	public void saveJourney (int journeyIndex, GA_Journey journey) {
		this.journeys[journeyIndex] = journey;
	}

	/*
	Retrieve the journey at the element index specified.
	 */
	public GA_Journey getJourney (int journeyIndex) {
		return this.journeys[journeyIndex];
	}

	/*
	Retrieve the fittest (i.e. shortest) journey in this population
	 */
	public GA_Journey getFittestJourney() {
		// Default journey will be the first one in the array.
		GA_Journey fittest = this.journeys[0];
		// Loop through the journeys array to find the fittest one
		for (int f = 1; f < populationSize(); f++) {
			if (fittest.getJourneyFitness() <= getJourney(f).getJourneyFitness()) {
				fittest = getJourney(f);
			}
		}
		return fittest;
	}

	/*
	Retrieve the population size (i.e. number of journeys)
	 */
	public int populationSize() {
		return journeys.length;
	}

}
