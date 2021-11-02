import java.lang.System;import java.math.RoundingMode;import java.text.DecimalFormat;import java.util.Scanner;public class tsp {	public static void main(String[] args) {		displayMenu(true);		/*		// Log the start nanoTime		long start = System.nanoTime();        // Parse data file		FileReader.readFile("files/test1tsp.txt");		long endReadFile = System.nanoTime();		// Populate an integer array with the city indexes (numbers)		int sequence[] = new int[Utilities.cities.size() - 1];		// Populate the integer array with the city indexes, skipping the first city		int n = 0;		for (int c = 0; c < Utilities.cities.size(); c++) {			if (Utilities.cities.get(c).getIndex() != 1) {				sequence[n] = Utilities.cities.get(c).getIndex();				n++;			}		}		long endPrepareArrays = System.nanoTime();		try {			Utilities.generatePermutations(sequence); // Generate all route permutations		} catch (Exception e) {			System.out.println("An error has occurred - " + e.getMessage());			e.printStackTrace();		}		long endPermutations = System.nanoTime();		int minVoyage = Utilities.solveTSP_bruteForce(); // Solve the TSP using brute force		long endBruteForce = System.nanoTime();		System.out.print ("Start: ");		for (int a = 0; a <= Utilities.cities.size(); a++) { // Display the answer			System.out.print (Utilities.permutations[minVoyage][a]);			if (a == (Utilities.cities.size())) { // Last element?				System.out.println (". Total (shortest) distance: " + Utilities.voyageDistance[minVoyage]);			} else {				System.out.print (" >> ");			}		}		long endDisplayBruteForce = System.nanoTime();		 */		/*		Display timings		System.out.println ("\n");		System.out.println ("Time to read file: " + (endReadFile - start) + " nanoseconds");		System.out.println ("Time to prepare arrays: " + (endPrepareArrays - endReadFile) + " nanoseconds");		System.out.println ("Time to generate permutations: " + (endPermutations - endPrepareArrays) + " nanoseconds");		System.out.println ("Time to solve TSP using Brute Force: " + (endBruteForce - endPermutations) + " nanoseconds");		System.out.println ("Time to display solution: " + (endDisplayBruteForce - endBruteForce) + " nanoseconds");		System.out.println ("Total time taken: " + (endDisplayBruteForce - start) + " nanoseconds");		 */		/*		// Testing		// List of cities and their coordinates		System.out.println ("FromCity,ToCity,FromTo,Distance");		for (City c : Utilities.cities) {			//System.out.println("\nCity,X,Y");			//System.out.println (c.getIndex() + "," + c.getX() + "," + c.getY());			// Lists of cities and their Euclidean distance to each of the other cities			for (City c2 : Utilities.cities) {				if (!c.equals(c2)) {					System.out.println (c.getIndex() + "," + c2.getIndex() + "," + c.getIndex() + c2.getIndex() + "," + c.distanceToCity(c2));				}			}		}		*/		/*		// List of permutations		for (int p1 = 0; p1 < Utilities.permutations.length; p1++) {			for (int p2 = 0; p2 < Utilities.permutations[p1].length; p2++) {				System.out.print (Utilities.permutations[p1][p2] + ",");			}			System.out.println();		}		 */	}	private static void displayMenu(boolean displayChoices) {		long start = 0;		if (displayChoices) {			System.out.println ("\n            TSP - Console Menu\n-------------------------------------------\n");			System.out.println ("A: Load file");			System.out.println ("B: Generate all permutations");			System.out.println ("C: Solve TSP using Brute Force (exhaustive)");			System.out.println ("D: Solve TSP using Depth First Search (DFS)");			System.out.println ("\n-------------------------------------------\n");			System.out.println ("X: Exit");		}		System.out.println ("\nPlease make your choice: ");		Scanner myScanner = new Scanner (System.in);		String userInput = myScanner.nextLine();		switch (userInput.toUpperCase()) {			case "A":	// Load file				System.out.println ("Please enter the filename you wish to load: ");				String fileName = myScanner.nextLine();				start = System.nanoTime();				try {					FileReader.readFile("files/" + fileName);					if (Utilities.cities.size() > 0) {						System.out.println ("File " + fileName + " has been loaded. "								+ Utilities.cities.size() + " localities loaded.");					}				} catch (Exception e) {					System.out.println ("tsp.displayMenu - an error has occurred." + e.getMessage());					displayMenu(false);				}				long readFileNanoTime = (System.nanoTime() - start);				// Display the time taken				displayNanoTime(readFileNanoTime);				// Bring up the menu				displayMenu(false);				break;			case "B":	// Generate permutations				// Create an integer array with the city indexes (numbers)				int[] sequence = new int[Utilities.cities.size() - 1];				// Populate the Integer array with the city indexes, skipping the first city				int n = 0;				for (int c = 0; c < Utilities.cities.size(); c++) {					if (Utilities.cities.get(c).getIndex() != 1) {						sequence[n] = Utilities.cities.get(c).getIndex();						n++;					}				}				start = System.nanoTime();				Utilities.generatePermutations(sequence, false);				long generatePermutationsNanoTime = (System.nanoTime() - start);				System.out.println (Utilities.permutations.length + " permutations generated.");				// Display the time taken				displayNanoTime(generatePermutationsNanoTime);				// Bring up the menu				displayMenu(false);				break;			case "C":	// Solve the TSP using Brute Force				if (Utilities.permutations[0][0] > 0) {					start = System.nanoTime();					int minVoyage = Utilities.solveTSP_bruteForce();					long bruteForceNanoTime = (System.nanoTime() - start);					for (int a = 0; a <= Utilities.cities.size(); a++) { // Display the answer						System.out.print (Utilities.permutations[minVoyage][a]);						if (a == (Utilities.cities.size())) { // Last element?							System.out.println (". Total (shortest) distance: " + Utilities.voyageDistance[minVoyage]);						} else {							System.out.print (" >> ");						}					}					// Display the time taken					displayNanoTime(bruteForceNanoTime);					// Bring up the menu				} else {					System.out.println ("Permutations have not been generated. " +							"\nPlease select option [B] to generate them first.");				}				displayMenu(false);				break;//				case "D":	Solve TSP using Depth First Search (DFS)//					break;			case "X":	// Exit				System.exit(0);				break;			default:				System.out.println ("Invalid input. Please try again: ");				displayMenu(false);				break;		}	}	private static void displayNanoTime (long timeSpan) {		DecimalFormat formatNS = new DecimalFormat("###.######");		formatNS.setRoundingMode(RoundingMode.CEILING);		System.out.println ("\nTime taken: " + formatNS.format(timeSpan * 0.000000001)				+ " seconds (" + timeSpan + " nano seconds).");	}}