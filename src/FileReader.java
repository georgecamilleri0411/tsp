import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	// Reads the file passed as argument and stores it in an array of int with 3 dimensions
	public static void readFile(String filePath) {
		try {
			File textFile = new File(filePath);
			Scanner fileReader = new Scanner(textFile);
			while (fileReader.hasNextLine()) {
				int[] lineData = parseLine(fileReader.nextLine());
				Utilities.cities.add(new City(lineData[0], lineData[1], lineData[2]));
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 Parses the data line read and returns an array of Integers
	 */
	private static int[] parseLine(String dataLine) {
		try {
			// Replace multiple spaces by a single space using Regex
			dataLine = dataLine.replaceAll("^ +| +$|( )+", "$1");

			/*
			// Remove spaces at both ends of the string
			dataLine = dataLine.trim();

			// Loops through the string to replace multiple (2) spaces by a single space
			while (dataLine.contains("  ")) {
				dataLine = dataLine.replace("  ", " ");
			}
			 */

			// Splits the string into a String array, then loops through it to populate the Integer array
			String[] line = dataLine.split(" ");
			int[] output = new int[line.length];

			for (int i = 0; i < line.length; i++) {
				if (isNumeric(line[i])) {
					output[i] = Integer.parseInt(line[i]);
				} else {
					output[i] = Integer.parseInt(null);
				}
			}

			return output;
		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/*
	Returns True if the value passed is numeric
	 */
	public static boolean isNumeric(String input) {

		if (input == null || input.trim().length() == 0) {
			return false;
		}
		try {
			int i = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
