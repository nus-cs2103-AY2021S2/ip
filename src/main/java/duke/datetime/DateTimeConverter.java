package duke.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Converter to convert date and time string inputs into LocalDateTime objects.
 *
 * @author  Nicole Ang
 */
public class DateTimeConverter {
    protected String[] inputSplit;

    /**
     * Constructs a new DateTimeConverter object to convert date and time Strings to LocalDate and LocalTime.
     *
     * @param inputSplit    String input that has been split into description, date, from time and to time.
     */
    public DateTimeConverter(String[] inputSplit) {
        this.inputSplit = inputSplit;
    }

    /**
     * Returns LocalDate object converted from user inputted date String of format dd-MM-yyyy.
     *
     * @return LocalDate created from inputted date.
     */
    public LocalDate convertDate() {
        String date = inputSplit[1].substring(3);

        if (date.length() > 10) {
            date = date.substring(0, date.length() - 1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = LocalDate.parse(date, formatter);
        return formattedDate;
    }

    /**
     * Returns LocalTime object converted from user inputted time of format h AM/PM.
     *
     * @param s Type of time, start (indicated by "from") or end (indicated by "to") time.
     * @return LocalTime created from input time.
     */
    public LocalTime convertTime(String s) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h a");

        if (s.equals("from")) {
            String from = inputSplit[2].substring(5, inputSplit[2].length() - 1).toUpperCase();
            LocalTime formattedFrom = LocalTime.parse(from, timeFormatter);
            return formattedFrom;
        } else if (s.equals(("to"))) {
            String to = inputSplit[3].substring(3).toUpperCase();
            LocalTime formattedTo = LocalTime.parse(to, timeFormatter);
            return formattedTo;

        } else {
            throw(new IllegalArgumentException());
        }
    }
}
