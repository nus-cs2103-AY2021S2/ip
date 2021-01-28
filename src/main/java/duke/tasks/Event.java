package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * returns string representation of Event when saving locally.
     * @return string of Event when saving.
     */
    public String saveString() {
        return isDone ? "E --- 1 --- " + description + " --- " + at : "E --- 0 --- " + description + " --- " + at;
    }

    /**
     * Converts user input for date in yyyy-mm-dd format into MMM d yyyy format.
     * @param input a string representation of a date in the format yyyy-mm-dd.
     * @return string representation of date in MMM d yyyy format.
     */
    public static String convertToDate(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeException e) {
            return input;
        }
    }

    /**
     * returns string representation of Event.
     * @return string of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Event.convertToDate(at) + ")";
    }
}
