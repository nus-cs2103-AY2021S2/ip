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
     * String splitter, when characters exceed length, split the string into two lines.
     *
     * @param str Input string.
     * @param length Desired length of a line.
     * @return Spitted string.
     */
    public static String newLiner(String str, int length) {
        assert length > 0 : "length is not handled correctly by printer.";
        StringBuilder resultStr = new StringBuilder();
        while (str.length() > length) {
            resultStr.append(str, 0, length).append("\n");
            str = str.substring(length);
        }
        return resultStr.toString() + str + "\n";
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
