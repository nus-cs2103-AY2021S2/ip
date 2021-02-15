package snom.common.util;

import java.util.StringTokenizer;

/**
 * Helper methods used to validate and format inputs for Snomio.
 */
public class SnomioUtil {

    /**
     * Returns true if tokenizer is null or it has no more tokens left.
     *
     * @param tokenizer tokenizer that stores string input
     */
    public static boolean hasNoTokens(StringTokenizer tokenizer) {
        return tokenizer == null || !tokenizer.hasMoreTokens();
    }

    /**
     * Returns true if given String is able to be converted to Integer.
     *
     * @param string  A string
     */
    public static boolean isValidInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if given String is able to be converted to Double.
     *
     * @param string  A string
     */
    public static boolean isValidDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
