package duke.parser;

import duke.exceptions.DukeDateParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {

    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("\\d+[-]\\d+[-]\\d+");
    private static final String MISSING_DATE_ERROR_MESSAGE = "YOU ARE MISSING OUT A DATE IN (yyyy-mm-dd) format!";
    private static final String INVALID_DATE_ERROR_MESSAGE = "Date must be a valid date in the calendar!";

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

    public static String extractDate(String input) throws DukeDateParseException {
        Matcher m = DATE_FORMAT_PATTERN.matcher(input);
        if (m.find()) {
            return m.group(0);
        } else {
            throw new DukeDateParseException(MISSING_DATE_ERROR_MESSAGE);
        }
    }

    public static LocalDate parseLocalDate(String input) throws DukeDateParseException {
        String dateString = DateParser.extractDate(input);
        try {
            return DateParser.parseDate(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeDateParseException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    public static String replaceDate(String stringWithOldDate , LocalDate newLocalDate) {
        try {
            String oldDateString = DateParser.extractDate(stringWithOldDate);
            String newDateString = newLocalDate.format(DateTimeFormatter.ofPattern("MMM d yyyy" ));
            String stringWithNewDate = stringWithOldDate.replaceAll(oldDateString, newDateString);
            return stringWithNewDate;
        } catch (DukeDateParseException e) {
            return stringWithOldDate;
        }
    }
}