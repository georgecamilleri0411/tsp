import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class fileReader {

	/*
	Array to store the coordinates
	 */
	ArrayList<Integer> dataLine = new ArrayList<>();

	/*
	Reads the file passed as argument and stores it in an array of int with 3 dimensions
	 */
	public static void readFile(String filePath) {
		try {
			File textFile = new File(filePath);
			Scanner fileReader = new Scanner(textFile);
			while (fileReader.hasNextLine()) {
				String dataLine = fileReader.nextLine();

				// TEST: System.out.println(dataLine);

				/*
				CONTINUE HERE
				for (int i = 0; i < parseLine(dataLine).length; i++) {
					// TEST: System.out.println (parseLine(dataLine)[i]);

				}
				*/
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static int[] parseLine(String dataLine) {
		try {
			String[] line = dataLine.split(" ");
			int[] output = new int[line.length];
			for (int i = 0; i < line.length; i++) {
				output[i] = Integer.parseInt(line[i]);
			}
			return output;
		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
