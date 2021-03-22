package duke;

/**
 * a class that provides helper function for others
 */
public class Helper {
    /**
     * Quick method to check whether the string passed is an integer or not
     *
     * @return a boolean
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
