package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String SHORT_HAND = "D";
    protected final String by;
    private String localDateStr;
    private LocalDate localDate;

    /**
     * Constructs the Deadline class.
     * @param taskDescription description of the task
     * @param by the deadline of the task
     */
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
        this.localDateStr = null;
    }

    /**
     * Formats text version of a date to LocalDate.
     * @param yearMonthDay the date to be parsed
     * @throws DukeException exception is thrown if the date is not in the correct format
     */
    public void dateFormatter(String yearMonthDay) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
            this.localDate = LocalDate.parse(yearMonthDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.localDateStr = dateTimeFormatter.format(localDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date cannot be parsed");
        }
    }

    /**
     * Formats text version of a date from the yyyy-MM-dd HH:mm format to MMM-dd-yyyy HH:mm format.
     * @param dateTimeText date in the yyyy-MM-dd HH:mm format
     * @throws DukeException exection if dateTimeText cannot be parsed
     */
    public void dateTimeFormatter(String dateTimeText) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.localDateStr = formatter.format(dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date and time cannot be parsed");
        }
    }

    //TODO remove empty spaces, if any, when performing this.by.split(" ")
    @Override
    public String toString() {
        try {
            if (this.by.split(" ").length == 1) {
                dateFormatter(this.by);
            } else {
                dateTimeFormatter(this.by);
            }

        } catch (DukeException e) {
            System.out.println("\t\t" + e.getMessage());
        }
        if (localDate == null) {
            return "[" + SHORT_HAND + "]" + super.toString() + " (by: " + this.by + ")";
        } else {
            return "[" + SHORT_HAND + "]" + super.toString() + " (by: " + localDateStr + ")";
        }
    }
}

