package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeDateParseException;

public class DateParser {

    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("\\d+[-]\\d+[-]\\d+");
    private static final String MISSING_DATE_ERROR_MESSAGE = "YOU ARE MISSING OUT A DATE IN (yyyy-mm-dd) format!";
    private static final String INVALID_DATE_ERROR_MESSAGE = "Date must be a valid date in the calendar!";

    /**
     * parses the date string if it is of broad format (yyyy-MM-dd)
     * and converts it into a LocalDate object if it is of broad format (yyyy-MM-dd)
     *
     * @param input string of only the date.
     * @return string containing the date.
     */

    public static LocalDate parseDate(String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(input, dateTimeFormatter);
    }

    /**
     * Extracts date within the string in the format d-d-d, d stand for arbitrary number of digits. Only the
     * first occurrence of such a date in the form d-d-d is parsed.
     *
     * @param input string to be parsed.
     * @return The subtring containing the date only.
     * @throws DukeDateParseException when the date of the required format is not found in the string.
     */

    public static String extractDate(String input) throws DukeDateParseException {
        Matcher m = DATE_FORMAT_PATTERN.matcher(input);
        if (m.find()) {
            return m.group(0);
        } else {
            throw new DukeDateParseException(MISSING_DATE_ERROR_MESSAGE);
        }
    }

    /**
     * Extracts the first occurence of a substring containing the date, and then
     * parses it into a localDate Object.
     * The date substring must be in the following format (d-d-d) where d
     * represents an arbitrary number of digits.
     *
     * @param input the string to extract and parse the date from
     * @return the LocalDate that is obtained from parsing the date inside the string
     * @throws DukeDateParseException throws error when it is not a valid date
     */

    public static LocalDate parseStringContainingDate(String input) throws DukeDateParseException {
        String dateString = DateParser.extractDate(input);
        try {
            return DateParser.parseDate(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeDateParseException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    /**
     * Replaces all occurrences of a date inside the string with a new formatted date.
     * All the replaced dates are in the format
     * (MMM-dd-yyy) for example "Oct 9 2021".
     *
     * @param stringWithOldDate string containing a date inside as a substring
     * @param newLocalDate the new local date to replace the old date.
     * @return the resulting new string containing the formatted new date in place of the old date
     */

    public static String replaceDate(String stringWithOldDate , LocalDate newLocalDate) {
        try {
            String oldDateString = DateParser.extractDate(stringWithOldDate);
            String newDateString = newLocalDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String stringWithNewDate = stringWithOldDate.replaceAll(oldDateString, newDateString);
            return stringWithNewDate;
        } catch (DukeDateParseException e) {
            return stringWithOldDate;
        }
    }
}
