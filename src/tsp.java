import java.io.FileNotFoundException;import java.lang.System;import java.util.Locale;import java.util.Scanner;public class tsp {	public static void main(String[] args) {		displayMenu();		// Log the start nanoTime		long start = System.nanoTime();        // Parse data file		//FileReader.readFile("files/test1-20.txt");		//FileReader.readFile("files/test1tsp.txt");		/*		for (int i = 0; i < Utilities.getLocalities().size(); i++) {			System.out.println ("Locality: " + Utilities.getLocalities().get(i));			System.out.println ("X: " + Utilities.getLocX().get(i));			System.out.println ("Y: " + Utilities.getLocY().get(i));		}		*///		for (Distance l : Utilities.getLocDistances()) {//			System.out.println (l.getFromLocality() + " >> " + l.getToLocality() + " : " + l.getDistance());//		}		long endReadFile = System.nanoTime();/*		// Populate an integer array with the city indexes (numbers)		int sequence[] = new int[Utilities_001.cities.size() - 1];		// Populate the integer array with the city indexes, skipping the first city		int n = 0;		for (int c = 0; c < Utilities_001.cities.size(); c++) {			if (Utilities_001.cities.get(c).getIndex() != 1) {				sequence[n] = Utilities_001.cities.get(c).getIndex();				n++;			}		}		long endPrepareArrays = System.nanoTime();		try {			Utilities_001.generatePermutations(sequence); // Generate all route permutations		} catch (Exception e) {			System.out.println("An error has occurred - " + e.getMessage());			e.printStackTrace();		}		long endPermutations = System.nanoTime();		int minVoyage = Utilities_001.solveTSP_bruteForce(); // Solve the TSP using brute force		long endBruteForce = System.nanoTime();		System.out.print ("Start: ");		for (int a = 0; a <= Utilities_001.cities.size(); a++) { // Display the answer			System.out.print (Utilities_001.permutations[minVoyage][a]);			if (a == (Utilities_001.cities.size())) { // Last element?				System.out.println (". Total (shortest) distance: " + Utilities_001.voyageDistance[minVoyage]);			} else {				System.out.print (" >> ");			}		}		long endDisplayBruteForce = System.nanoTime();*//*		Display timings		System.out.println ("\n");		System.out.println ("Time to read file: " + (endReadFile - start) + " nanoseconds");		System.out.println ("Time to prepare arrays: " + (endPrepareArrays - endReadFile) + " nanoseconds");		System.out.println ("Time to generate permutations: " + (endPermutations - endPrepareArrays) + " nanoseconds");		System.out.println ("Time to solve TSP using Brute Force: " + (endBruteForce - endPermutations) + " nanoseconds");		System.out.println ("Time to display solution: " + (endDisplayBruteForce - endBruteForce) + " nanoseconds");		System.out.println ("Total time taken: " + (endDisplayBruteForce - start) + " nanoseconds");*/		/*		// Testing		// List of cities and their coordinates		System.out.println ("FromCity,ToCity,FromTo,Distance_001");		for (City c : Utilities_001.cities) {			//System.out.println("\nCity,X,Y");			//System.out.println (c.getIndex() + "," + c.getX() + "," + c.getY());			// Lists of cities and their Euclidean distance to each of the other cities			for (City c2 : Utilities_001.cities) {				if (!c.equals(c2)) {					System.out.println (c.getIndex() + "," + c2.getIndex() + "," + c.getIndex() + c2.getIndex() + "," + c.distanceToCity(c2));				}			}		}		*/		/*		// List of permutations		for (int p1 = 0; p1 < Utilities_001.permutations.length; p1++) {			for (int p2 = 0; p2 < Utilities_001.permutations[p1].length; p2++) {				System.out.print (Utilities_001.permutations[p1][p2] + ",");			}			System.out.println();		}		 */	}	/*	Displays a menu	*/	private static void displayMenu() {		System.out.println ("            TSP - Console Menu\n-------------------------------------------\n");		System.out.println ("A: Load file");		System.out.println ("B: Generate all permutations");		System.out.println ("C: Solve TSP using Brute Force (exhaustive)");		System.out.println ("D: Solve TSP using Depth First Search (DFS)");		System.out.println ("\n-------------------------------------------\n");		System.out.println ("X: Exit\n");		System.out.println ("Please make your choice: ");		Scanner myScanner = new Scanner (System.in);		String userInput = myScanner.nextLine();		switch (userInput.toUpperCase()) {			case "A":				System.out.println ("Please enter the filename you wish to load: ");				String fileName = myScanner.nextLine();				try {					FileReader.readFile("files/" + fileName);					if (Utilities.getLocalities().size() > 0) {						System.out.println ("File " + fileName + " has been loaded. "								+ Utilities.getLocalities().size() + " localities loaded.");					}				} catch (Exception e) {					System.out.println ("tsp.displayMenu - an error has occurred." + e.getMessage());					displayMenu();				}				displayMenu();				break;			case "B":				int[] sequence = new int[Utilities.localities.size()];				for (int i = 0; i < sequence.length; i++) {					sequence[i] = Utilities.getLocalities().get(i).intValue();				}				Utilities.generatePermutations(sequence);				System.out.println (Utilities.getPermutations().length + " permutations generated.");				break;			case "C":				break;//				case "D"://					break;			case "X":				System.exit(0);				break;			default:				System.out.println ("Invalid input. Please try again: ");				displayMenu();				break;		}	}}