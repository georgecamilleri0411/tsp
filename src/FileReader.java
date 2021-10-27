import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {

    String fileData[];

    /*
    Returns an array of String, containing the file contents parsed in pipe-delimited format
     */
    public static void readFile(String filePath) {

        String nextLine = "";
        int[] duplX;    // Used for searching for duplicate localities
        int[] duplY;    // Used for searching for duplicate localities

        try {
            File textFile = new File(filePath);
            Scanner fileScanner = new Scanner(textFile);
            while (fileScanner.hasNextLine()) {
                // Read the next line into a String and replace and tab characters with a single space character
                nextLine = fileScanner.nextLine().replaceAll("\t", " ").trim();
                if (String.valueOf(nextLine) != null) {
                    int[] lineData = parseLine(nextLine);

//                    if (lineData != null) {
//                        // Check for duplicate localities
//                        duplX = Arrays.binarySearch(Utilities.getLocalities(), lineData[1]);
//                    }

                    // Check for duplicate cities
//                    for (City c : Utilities_001.cities) {
//                        dupl = false;
//                        if ((c.getX() == lineData[1]) && (c.getY() == lineData[2])) {
//                            dupl = true;
//                            break;
//                        }
//                    }
//                    if (!dupl) {
//                        Utilities_001.cities.add(new City(lineData[0], lineData[1], lineData[2]));
//                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred - " + e.getMessage());
            System.out.println("Line data: " + nextLine);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error has occurred - " + e.getMessage());
            System.out.println("Line data: " + nextLine);
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

            // Splits the string into a String array, then loops through it to populate the Integer arrays
            String[] line = dataLine.split(" ");
            int[] output = new int[line.length];

            for (int i = 0; i < line.length; i++) {
                if (Utilities.valueIsNumeric(line[i])) {
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


}