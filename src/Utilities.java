import java.util.ArrayList;

public class Utilities {

    protected static ArrayList<Integer> localities = new ArrayList();
    protected static ArrayList<Integer> locX = new ArrayList();
    protected static ArrayList<Integer> locY = new ArrayList();
    private static ArrayList<Distance> locDistances = new ArrayList();

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

}
