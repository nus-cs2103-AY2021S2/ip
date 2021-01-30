package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Encapsulates any behaviour related to DateTime objects contained within Deadline and Event tasks.
 */
public class DateTime {

    private final LocalDateTime dateTime;

    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Converts string into LocalDateTime object.
     *
     * @param date String representing date
     * @return LocalDateTime object
     * @throws DukeException if format of the date String is invalid
     */
    public static LocalDateTime convertStringToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            throw new DukeException("There was something wrong with the format of your date and/or time.\n"
                    + "Make sure it's in the format <dd/MM/yyyy HHmm>!");
        }
    }

    /**
     * Returns String representing DateTime of tasks to be printed to the CLI.
     *
     * @return String representing DateTime of tasks
     */
    public String getDateTimeForDisplay() {
        return " (by: " + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }

    /**
     * Returns String representing DateTime of tasks to be stored in data file.
     *
     * @return String representing DateTime of tasks
     */
    public String getFormattedStorageString() {
        return dateTime.toString();
    }
}
