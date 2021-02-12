package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {

    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("\\d+[-]\\d+[-]\\d+");
    /**
     * parses the date and returns the string containing the date if it is of broad format ( yyyy-MM-dd)
     *
     * @param input string to be parsed.
     * @return string containing the date.
     */

    public static LocalDate parseDate(String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(input, dateTimeFormatter);
    }

    /**
     * Extracts date within the string in the format d - d - d, d stand for arbitrary number of digits.
     *
     * @param input string to be parsed.
     * @return The subtring containing the date only.
     */

    public static String extractDate(String input) {
        Matcher m = DATE_FORMAT_PATTERN.matcher(input);
        if (m.find()) {
            return m.group(0);
        } else {
            return "";
        }
    }
}