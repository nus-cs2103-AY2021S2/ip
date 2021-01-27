/**
 * A class that is used to align text.
 */

public class Aligner {
    public Aligner() { }

    /**
     * Returns a string that is properly aligned.
     * @param s the String that needs to be aligned
     * @return an aligned String
     */
    public static String align(String s) {
        int stringLength = s.length();
        int LINELENGTH = 73;
        int left = (int) Math.ceil((LINELENGTH - stringLength) / 2);
        int right = (int) Math.floor((LINELENGTH - stringLength) / 2);
        String newString;
        newString = String.format("%" + left + "s", " ") + s
                + String.format("%" + right + "s", " ");
        return newString;
    }
}
