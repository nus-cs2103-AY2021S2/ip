package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of Task which has a description and date.
 * It also maintains a state of isDone.
 */
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
     * Returns string representation of Event when saving locally.
     *
     * @return String of Event when saving.
     */
    public String saveString() {
        return isDone ? "E --- 1 --- " + description + " --- " + at : "E --- 0 --- " + description + " --- " + at;
    }

    /**
     * Converts user input for date in yyyy-mm-dd format into MMM d yyyy format.
     *
     * @param input A string representation of a date in the format yyyy-mm-dd.
     * @return String representation of date in MMM d yyyy format.
     */
    public static String convertToDate(String input) {
        assert input != null;

        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeException e) {
            return "Error: " + e;
        }
    }

    /**
     * Returns string representation of Event.
     *
     * @return String of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Event.convertToDate(at) + ")";
    }
}
