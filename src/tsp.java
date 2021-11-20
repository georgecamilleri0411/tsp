import java.lang.System;import java.math.RoundingMode;import java.text.DecimalFormat;import java.util.ArrayList;import java.util.Scanner;public class tsp {	public static void main(String[] args) {		displayMenu(true);	}	private static void displayMenu(boolean displayChoices) {	/*	TODO: Implement Depth First Search (DFS), using randomisation. Also, to try and improve Brute Force by generating permutations in groups of 5000 to avoid out of memory errors.	TODO: Implement Genetic Algorithm (using Mutations?)	 */		long start = 0;		if (displayChoices) {			System.out.println ("\n            TSP - Console Menu\n-------------------------------------------\n");			System.out.println ("A: Load file");			System.out.println ("B: Generate all permutations");			System.out.println ("C: Solve TSP using Brute Force (exhaustive)");			System.out.println ("D: Solve TSP using Best First Search (BeFS)");			System.out.println ("\n-------------------------------------------\n");			System.out.println ("X: Exit");		}		System.out.println ("\nPlease make your choice: ");		Scanner myScanner = new Scanner (System.in);		String userInput = myScanner.nextLine();		switch (userInput.toUpperCase()) {			case "A":	// Load file				// Clear the distances and distancesHash ArrayLists				Utilities.distances.clear();				Utilities.distancesHash.clear();				System.out.println ("Please enter the filename you wish to load: ");				String fileName = myScanner.nextLine();				start = System.nanoTime();				try {					FileReader.readFile("files/" + fileName);					if (Utilities.cities.size() > 0) {						System.out.println ("File " + fileName + " has been loaded. "								+ Utilities.cities.size() + " localities loaded.");					}				} catch (Exception e) {					System.out.println ("tsp.displayMenu - an error has occurred." + e.getMessage());					displayMenu(false);				}				long readFileNanoTime = (System.nanoTime() - start);				// Display the time taken				displayNanoTime(readFileNanoTime);				// Bring up the menu				displayMenu(false);				break;			case "B":	// Generate permutations				// Display a warning to the user if the number of cities exceeds 12				if (Utilities.cities.size() > 12) {					System.out.println ("There are too many cities to be able to generate and store all permutations!");				} else {					// Create an integer array with the city indexes (numbers)					int[] sequence = new int[Utilities.cities.size() - 1];					// Populate the Integer array with the city indexes, skipping the first city					int n = 0;					for (int c = 0; c < Utilities.cities.size(); c++) {						if (Utilities.cities.get(c).getIndex() != 1) {							sequence[n] = Utilities.cities.get(c).getIndex();							n++;						}					}					start = System.nanoTime();					Utilities.generatePermutations(sequence, false);					long generatePermutationsNanoTime = (System.nanoTime() - start);					System.out.println (Utilities.permutations.length + " permutations generated.");					// Display the time taken					displayNanoTime(generatePermutationsNanoTime);				}				// Bring up the menu				displayMenu(false);				break;			case "C":	// Solve the TSP using Brute Force				if (Utilities.permutations != null && Utilities.permutations[0][0] > 0) {					start = System.nanoTime();					int minVoyage = Utilities.solveTSP_bruteForce();					long bruteForceNanoTime = (System.nanoTime() - start);					// Display the answer					for (int a = 0; a <= Utilities.cities.size(); a++) {						System.out.print (Utilities.permutations[minVoyage][a]);						if (a == (Utilities.cities.size())) { // Last element?							System.out.println (". Total (shortest) distance using Brute Force: " + Utilities.voyageDistance[minVoyage]);						} else {							System.out.print (" >> ");						}					}					// Display the time taken					displayNanoTime(bruteForceNanoTime);				} else {					System.out.println ("Permutations have not been generated. " +							"\nPlease select option [B] to generate them first.");				}				// Bring up the menu				displayMenu(false);				break;				case "D":	// Solve TSP using Best First Search (BeFS)					ArrayList<Integer> input = new ArrayList();					for (int l = 0; l < Utilities.cities.size(); l++) {						input.add(Utilities.cities.get(l).getIndex());					}					start = System.nanoTime();					ArrayList<Integer> output = Utilities.solveTSP_GreedyBeFS(input);					long befsNanoTime = (System.nanoTime() - start);					// Display the answer					for (int o = 0; o < output.size(); o++) {						System.out.print (output.get(o));						if (o < (output.size() - 1)) {							System.out.print (" >> ");						} else {							System.out.println (". Path found using Best First Search (Greedy Best Neighbour). ");							System.out.println ("Total distance: " + Utilities.getVoyageDistance(output));						}					}					// Display the time taken					displayNanoTime(befsNanoTime);					// Bring up the menu					displayMenu(false);					break;			case "E":	// Solve TSP using a Genetic Algorithm				int[] gaInput = new int[(Utilities.cities.size())];				for (int l = 0; l < Utilities.cities.size(); l++) {					gaInput[l] = Utilities.cities.get(l).getIndex();				}				start = System.nanoTime();				GeneticAlgorithm gaSolver = new GeneticAlgorithm(gaInput);				int[] gaOutput = gaSolver.solveTSP_GA();				long gaNanoTime = (System.nanoTime() - start);				// Display the answer				for (int o = 0; o < gaOutput.length; o++) {					System.out.print (gaOutput[o]);					if (o < (gaOutput.length - 1)) {						System.out.print (" >> ");					} else {						System.out.println (". Path found using a Genetic Algorithm. ");						//System.out.println ("Total distance: " + Utilities.getVoyageDistance(output));					}				}				// Display the time taken				displayNanoTime(gaNanoTime);				// Bring up the menu				displayMenu(false);				break;			case "X":	// Exit				System.exit(0);				break;			default:				System.out.println ("Invalid input. Please try again: ");				displayMenu(false);				break;		}	}	private static void displayNanoTime (long timeSpan) {		DecimalFormat formatNS = new DecimalFormat("###.######");		formatNS.setRoundingMode(RoundingMode.CEILING);		System.out.println ("\nTime taken: " + formatNS.format(timeSpan * 0.000000001)				+ " seconds (" + timeSpan + " nano seconds).");	}}