/**
 * Parses the input from user to a format used by rest of the files.
 */
public class Parser {
    public static String trimWhiteSpaces(String str) {
        return str.trim();
    }

    /**
     * Returns a String array with the zero index being the first group of characters which are not separated by
     * whitespace, and the first index being the rest of the characters in str.
     *
     * @param str input by the user.
     * @return String[] zero index being the first group of characters which are not separated by
     * whitespace, and the first index being the rest of the characters in str.
     */
    public static String[] firstAndRest(String str) {
        String[] res = new String[2];
        String[] split = str.split(" ");
        res[0] = split[0];
        String rest = "";
        for(int i = 1; i < split.length; i++) {
            rest += " " + split[i];
        }
        res[1] = rest;
        return res;
    }

    /**
     * Returns the parsed str as an integer.
     *
     * @param str input to be parsed to become an int.
     * @return int parsed str to an integer.
     * @throws NumberFormatException if str is not a number.
     */
    public static int makeToInt(String str) {
        try {
            return Integer.parseInt(Parser.trimWhiteSpaces(str));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(Ui.lineGetter() +
                    " Enter a number after command, 'done (number)' or 'delete (number)'\n"
                     + Ui.lineGetter());
        }
    }
}
