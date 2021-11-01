import java.util.ArrayList;

public class Utilities {

    // ArrayList of Integer to store the Localities (cities) indexes
    protected static ArrayList<Integer> localities = new ArrayList();
    // ArrayList of Integer to store each locality's X coordinate
    protected static ArrayList<Integer> locX = new ArrayList();
    // ArrayList of Integer to store each locality's Y coordinate
    protected static ArrayList<Integer> locY = new ArrayList();
    // ArrayList of Distance to store the distance for each locality to all other localities
    private static ArrayList<Distance> locDistances = new ArrayList();
    // Array of Integer to store all the different permutations.
    private static int[][] permutations
            = new int[getNumOfPermutations(localities.size())][(getNumOfPermutations(localities.size()) + 1)];

    /*
    Getters and setters
     */
    public static ArrayList<Integer> getLocalities() {
        return localities;
    }
    public static void setLocalities(ArrayList<Integer> localities) {
        Utilities.localities = localities;
    }

    public static ArrayList<Integer> getLocX() {
        return locX;
    }
    public static void setLocX(ArrayList<Integer> locX) {
        Utilities.locX = locX;
    }

    public static ArrayList<Integer> getLocY() {
        return locY;
    }
    public static void setLocY(ArrayList<Integer> locY) {
        Utilities.locY = locY;
    }

    public static ArrayList<Distance> getLocDistances() {return locDistances;}
    public static void setLocDistances(ArrayList<Distance> locDistances) {Utilities.locDistances = locDistances;}

    public static int[][] getPermutations() {return permutations;}
    public static void setPermutations(int[][] permutations) {Utilities.permutations = permutations;}

    /*
    Returns True if the String value passed is numeric
    */
    public static boolean valueIsNumeric(String input) {
        try {
            return (input.trim() != null && input.matches("[-+]?\\d*\\.?\\d+"));
        } catch (NumberFormatException e) {
            System.out.println ("Utilities.valueIsNumeric - A Number Format Exception has occurred: " + e.getMessage());
            return false;
        }
    }

    /*
    Calculates the Euclidean distance between 2 points on a plane by using the Pythagorean theorem
    */
    public static double getEuclideanDistance(int x1, int y1, int x2, int y2) {
        try {
            return Math.hypot(Math.abs(y1 - y2), Math.abs(x1 - x2));
        } catch (Exception e) {
            System.out.println("Utilities.getEuclideanDistance - An error has occurred - " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    /*
    Adds elements to the localities, locX and locY ArrayLists, from the int Array passed
     */
    public static void addLocality(int[] input) {
        try {
            getLocalities().add(input[0]);
            getLocX().add(input[1]);
            getLocY().add(input[2]);
        } catch (NumberFormatException e) {
            System.out.println ("Utilities.addLocality - A Number Format Exception has occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println ("Utilities.addLocality - An error has occurred: " + e.getMessage());
        }
    }

    /*
    Populates the locDistances ArrayList, so that the distances are all cached
     */
    public static void setLocDistances() {
        try {
            for (int c1 = 0; c1 < getLocalities().size(); c1++) {
                for (int c2 = 0; c2 < getLocalities().size(); c2++) {
                    if (c1 != c2) {
                        getLocDistances().add(new Distance(getLocalities().get(c1), getLocalities().get(c2),
                                getEuclideanDistance(getLocX().get(c1), getLocY().get(c1), getLocX().get(c2), getLocY().get(c2))));
                    }
                }
            }

        } catch (NumberFormatException e) {
            System.out.println ("Utilities.setLocDistances - A Number Format Exception has occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println ("Utilities.setLocDistances - An error has occurred: " + e.getMessage());
        }
    }

    // Get the number of different permutations possible
    public static int getNumOfPermutations(int numOfLocalities) {
        int permutations = 1;
        for (int i = 1; i < (numOfLocalities - 1); i++) {
            permutations += (i * permutations);
        }
        return permutations;
    }

    /*
	Generates all permutations of the integer array passed as argument.
	This method is used for the Brute Force solution.
	 */
    public static void generatePermutations (ArrayList<Integer> input) {

        try {
            setPermutations(new int[getNumOfPermutations(localities.size())][(localities.size() + 1)]);

            // Initialise an integer array for the (number of cities - 1), since city 1 will always start
            int[] sequence = new int[localities.size() - 1];

            // Permutations counter
            int p = 0;

		    /*
		    Add the values in their initial order into array input.
            Starting with the first city (i.e. starting point).
		    */
            permutations[p][0] = localities.get(0);
            // Loop through the input ArrayList and populate the input array
            for (int l = 1; l < input.size(); l++) {
                getPermutations()[p][(l)] = input.get(l).intValue();
            }
            // Add the last element (city 1 again)
            permutations[p][(input.size() + 1)] = localities.get(0);

            // The first permutation (default sequence) is ready. Increment counter
            p++;

            // Use iterations to swap array elements
            int i = 0;
            while (i < sequence.length) {
                if (sequence[i] < i) {
                    swapElements (input, i % 2 == 0 ? 0 : sequence[i], i);

                    if (p < getPermutations().length) {
                        // Add the first element (city 1)
                        permutations[p][0] = localities.get(0);
                        // Add the values in permutations input
                        for (int ct = 0; ct < input.size(); ct++) {
                            getPermutations()[p][(ct + 1)] = input.get(ct);
                        }
                        // Add the last element (city 1 again)
                        getPermutations()[p][(input.size() + 1)] = localities.get(0);

                    }

                    // Increment the permutations counter
                    p++;

                    sequence[i]++;
                    i = 0;
                } else {
                    sequence[i] = 0;
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("An error has occurred - " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    Swaps two elements of the input array (of Integer) with each other
    */
    private static void swapElements(ArrayList<Integer> input, int e1, int e2) {
        int tmp = input.get(e1);
        input.set(e1, input.get(e2));
        input.set(e2, tmp);
    }

}
