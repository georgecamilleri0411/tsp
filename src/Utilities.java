public class Utilities {

    protected static int[] localities;
    protected static int[] locX;
    protected static int[] locY;

    public static int[] getLocalities() {
        return localities;
    }

    public static void setLocalities(int[] localities) {
        Utilities.localities = localities;
    }

    public static int[] getLocX() {
        return locX;
    }

    public static void setLocX(int[] locX) {
        Utilities.locX = locX;
    }

    public static int[] getLocY() {
        return locY;
    }

    public static void setLocY(int[] locY) {
        Utilities.locY = locY;
    }

    /*
    Returns True if the String value passed is numeric
    */
    public static boolean valueIsNumeric(String input) {
        try {
            return (input.trim() != null && input.matches("[-+]?\\d*\\.?\\d+"));
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
