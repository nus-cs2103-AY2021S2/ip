package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with all special string matter for Duke.
 */
public class StringParser {

    // User should type in date and time in this manner.
    private static final DateTimeFormatter FORMAT_SCAN =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses user type in date and time.
     *
     * @param str Parsed user input.
     * @return LocalDateTime that represent the time.
     */
    public static LocalDateTime parseTime(String str) {
        return LocalDateTime.from(FORMAT_SCAN.parse(str));
    }

    /**

     * To generate underline.
     *
     * @param length Length of underline.
     * @return Underline.
     */
    public static String generateUnderline(int length) {
        return "_".repeat(length) + "\n";
    }

    /**
     * Simply check if a given string are made in ' '.
     *
     * @param str Input string.
     * @return Boolean value of the answer.
     */
    public static boolean isBlank(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
