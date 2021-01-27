package duke.util;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encodes & decodes the date as required.
 */
public class DateFormatter {

    /**
     * Encodes a date passed as a String into a LocalDate.
     * @param date Date in the yyyy-mm-dd format.
     * @return A LocalDate object.
     * @throws DukeException Occurs when a date had been passed in with the wrong format.
     */
    public static LocalDate encodeDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("An error occurred while encoding date,"
                    + " use the yyyy-mm-dd format.");
        }
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the dd MMM yyyy format.
     * @param date A LocalDate object.
     * @return A date String in the dd MMM yyyy format.
     */
    public static String decodeDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the yyyy-MM-dd format.
     * @param date A LocalDate object.
     * @return A date String in the yyyy-MM-dd format.
     */
    public static String decodeDateForStorage(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}