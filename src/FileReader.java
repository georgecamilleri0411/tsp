import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	// Array to store the coordinates
	public static ArrayList<City> cities = new ArrayList<>();

	// Reads the file passed as argument and stores it in an array of int with 3 dimensions
	public static void readFile(String filePath) {
		try {
			File textFile = new File(filePath);
			Scanner fileReader = new Scanner(textFile);
			while (fileReader.hasNextLine()) {
				int[] lineData = parseLine(fileReader.nextLine());
				cities.add(new City(lineData[0], lineData[1], lineData[2]));
			}
			fileReader.close();

			for (int x = 0; x < cities.size(); x++) {
				System.out.println("City: " + cities.get(x).getIndex() + "; X: " + cities.get(x).getX() + "; Y: " + cities.get(x).getY());
			}
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
			// Remove spaces at both ends of the string
			dataLine = dataLine.trim();

			// Loops through the string to replace multiple spaces by a single space
			while (dataLine.contains("  ")) {
				dataLine = dataLine.replace("  ", " ");
			}

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

		// if (input == null || input.equals(' ') || input.trim().length() == 0) {
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
