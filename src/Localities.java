import java.lang.Math;
import java.util.ArrayList;

import static java.lang.System.gc;

public class Localities {

    protected ArrayList<Integer> localityID = new ArrayList();
    protected ArrayList<Integer> localityX = new ArrayList();
    protected ArrayList<Integer> localityY = new ArrayList();
    protected ArrayList<String> localityFromTo = new ArrayList();
    protected ArrayList<Double> distance = new ArrayList();
    protected int numOfPermutations = -1;
    private int[][] permutations;
    private double[] permutationDistance;
    private int shortestJourney = -1;

    public ArrayList<Integer> getLocalityID() {
        return localityID;
    }
    public ArrayList<Integer> getLocalityX() {
        return localityX;
    }
    public ArrayList<Integer> getLocalityY() {
        return localityY;
    }
    public ArrayList<String> getLocalityFromTo() {
        return localityFromTo;
    }
    public ArrayList<Double> getDistance() {
        return distance;
    }
    public int getNumOfPermutations() {return numOfPermutations; }
    public int[][] getPermutations() {return permutations; }
    public double[] getPermutationDistance() {return permutationDistance; }
    public int getShortestJourney() {return shortestJourney; }

    public Localities() {}

    public Localities (ArrayList<Integer> _localityID, ArrayList<Integer> _localityX
            , ArrayList<Integer> _localityY) {
        if ((_localityID.size() == _localityX.size()) &&
                (_localityX.size() == _localityY.size()) || (_localityID.size() >= 0)) {
            this.localityID = _localityID;
            this.localityX = _localityX;
            this.localityY = _localityY;
            this.cacheDistances();
            this.setNumOfPermutations();
        } else {
            this.localityID = null;
            this.localityX = null;
            this.localityY = null;
            this.localityFromTo = null;
            this.distance = null;
        }
    }

    /*
    Calculates the Euclidean distance between 2 points on a plane by using the Pythagorean theorem
    */
    private static double getEuclideanDistance(int x1, int y1, int x2, int y2) {
        try {
            return Math.hypot(Math.abs(y1 - y2), Math.abs(x1 - x2));
        } catch (Exception e) {
            System.out.println("Localities.getEuclideanDistance - An error has occurred - " + e.getMessage());
            e.printStackTrace();
            return 0;
        } finally {
            gc();
        }
    }

    /*
    Calculates the number of different permutations possible according to the number of localities
    */
    private void setNumOfPermutations() {
        int numOfLocalities = this.localityID.size();
        int permutations = 1;
        try {
            for (int i = 1; i < (numOfLocalities - 1); i++) {
                permutations += (i * permutations);
            }
        } finally {
            this.numOfPermutations = permutations;
        }
    }

    /*
    Sets the distance between all the localities
    */
    private void cacheDistances() {
        try {
            for (int c1 = 0; c1 < this.localityID.size(); c1++) {
                for (int c2 = 0; c2 < this.localityID.size(); c2++) {
                    if (c1 != c2) {
                        this.localityFromTo.add(String.valueOf(c1).trim() + "|" + String.valueOf(c2).trim());
                        this.distance.add (this.getDistance(c1, c2));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Utilities.setDistances - An error has occurred - " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    Retrieves the distance between two localities
    */
    private double getDistance(int from, int to) {
        try {
            return getEuclideanDistance(this.localityX.get(from), this.localityY.get(from)
                    , this.localityX.get(to), this.localityY.get(to));
        } catch (Exception e) {
            System.out.println("Localities.getDistance - An error has occurred - " + e.getMessage());
            e.printStackTrace();
            return 0;
        } finally {
            gc();
        }
    }

    /*
	Calculates the distance of a whole journey involving all localities
 	*/
    private double getVoyageDistance(int[] voyage) {
        double output = 0;
        try {
            for (int v = 1; v < voyage.length; v++) {
                output += this.distance.get(this.localityFromTo.indexOf(String.valueOf(v - 1).trim() + "|"
                        + String.valueOf(v).trim()));
            }
        } catch (Exception e) {
            System.out.println("Utilities.getVoyageDistance2 - An error has occurred - " + e.getMessage());
            e.printStackTrace();
            return 0;
        } finally {
            gc();
            return output;
        }
    }

    /*
    Generates all different permutations for the localities, starting and ending in the first locality
    */
    public void solveTSP_BruteForce() {

        double shortestDistance = 0;

        // Instantiate an Integer array to store all localities except for the first one
        int[] input = new int[this.localityID.size() - 1];

        // Populate the Integer array with the city indexes, skipping the first city
        int n = 0;
        for (int c = 0; c < this.localityID.size(); c++) {
            if (this.localityID.get(c) != 1) {
                input[n] = this.localityID.get(c);
                n++;
            }
        }

        // Initialise arrays to hold the permutation data
        permutations = new int[this.numOfPermutations][(this.localityID.size() + 1)];
        permutationDistance = new double[this.numOfPermutations];

        // Initialise an integer array for the (number of cities - 1), since city 1 will always start
        int[] sequence = new int[this.localityID.size() - 1];

        // Permutations counter
        int p = 0;

		/*
		Add the values in their initial order in array input, starting with the first city (i.e. starting point).
	 	*/
        permutations[p][0] = this.localityID.get(0);
        // Loop through the input array and populate the permutations array
        for (int ct = 0; ct < input.length; ct++) {
            permutations[p][(ct + 1)] = input[ct];
        }
        // Add the last element (city 1 again)
        permutations[p][(input.length + 1)] = this.localityID.get(0);
        // The first permutation (default sequence) is ready.

        // Calculate the distance for this permutation.
        int[] voyage = new int[this.localityID.size()];
        for (int i = 0; i < voyage.length; i++) {
            voyage[i] = this.localityID.get(i);
        }
        // Add the ending locality (arrival point)
        voyage[(voyage.length - 1)] = this.localityID.get(0);
        permutationDistance[p] = getVoyageDistance(voyage);

        // Check if this permutation has the shortest distance so far
        if (shortestDistance == 0 || shortestDistance > permutationDistance[p]) {
            shortestDistance = permutationDistance[p];
            this.shortestJourney = p;
        }

        // Increment the permutation counter
        p++;

        // Use iterations to swap array elements
        int i = 0;
        while (i < sequence.length) {
            if (sequence[i] < i) {
                UtilitiesNEW.swapElements (input, i % 2 == 0 ? 0 : sequence[i], i);

                if (p < permutations.length) {
                    // Add the first element (city 1)
                    permutations[p][0] = this.localityID.get(0);
                    // Add the values in permutations input
                    for (int ct = 0; ct < input.length; ct++) {
                        permutations[p][(ct + 1)] = input[ct];
                    }
                    // Add the last element (city 1 again)
                    permutations[p][(input.length + 1)] = this.localityID.get(0);
                }

                // Calculate the distance for this permutation
                voyage = new int[this.localityID.size()];
                for (int j = 0; j < voyage.length; j++) {
                    voyage[j] = this.getLocalityID().get(j);
                }
                // Add the ending locality (arrival point)
                voyage[(voyage.length - 1)] = this.localityID.get(0);
                permutationDistance[p] = getVoyageDistance(voyage);

                // Check if this permutation has the shortest distance so far
                if (shortestDistance == 0 || shortestDistance > permutationDistance[p]) {
                    shortestDistance = permutationDistance[p];
                    this.shortestJourney = p;
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

        if (this.shortestJourney == -1) {
            System.out.println ("Something has gone wrong. Shortest journey was not found.");
        } else {
            // Display the shortest journey
            for (int s = 0; s <= this.localityID.size(); s++) {
                System.out.print (this.permutations[this.shortestJourney][s]);
                if (s < this.localityID.size()) {
                    System.out.print (" >> ");
                } else {
                    System.out.println("\nDistance: " + this.distance.get(this.shortestJourney));
                }
            }
        }
    }

}
