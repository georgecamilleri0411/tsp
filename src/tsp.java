import java.lang.System;public class tsp {	public static void main(String[] args) {        // Parse data file		FileReader.readFile("files/trainfile1.txt");		/*		for (int i = 0; i < Utilities.distances.size(); i++) {			System.out.println (i + ": " + Utilities.distances.get(i).getFromCity() + " to "					+ Utilities.distances.get(i).getToCity() + " : "					+ Utilities.distances.get(i).getDistance());		}		 */		// Populate an integer array with the city indexes (numbers)		int sequence[] = new int[Utilities.cities.size() - 1];		// Populate the integer array with the city indexes, skipping the first city		int n = 0;		for (int c = 0; c < Utilities.cities.size(); c++) {			if (Utilities.cities.get(c).getIndex() != 1) {				sequence[n] = Utilities.cities.get(c).getIndex();				n++;			}		}		Utilities.generatePermutations(sequence);		for (int p = 0; p < Utilities.permutations.length; p++) {			for (int q = 0; q < Utilities.cities.size() + 1; q++) {				System.out.print (Utilities.permutations[p][q] + " | ");			}			System.out.println();		}	}}