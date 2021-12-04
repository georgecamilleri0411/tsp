public class ChromosomeFitness {

	String[] solutionChromosome;

	public ChromosomeFitness(String[] _solutionChromosome) {
		solutionChromosome = _solutionChromosome;
	}

	/*
	Calculate the fitness of the candidate chromosome by comparing it to the solution chromosome.
	Any result < 1 means that the candidate chromosome is less fit than the solution chromosome.
	 */
	public double getChromosomeFitness (Chromosome candidateChromosome) {
		double solutionDistance = Utilities.getVoyageDistance(Utilities.convertArrayToIntArrayList
				(null, solutionChromosome, false));
		return (solutionDistance / candidateChromosome.getChromosomeDistance());
	}

}
