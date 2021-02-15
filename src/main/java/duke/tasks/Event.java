package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class Event
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an event in the task list
 */
public class Event extends Task {
    protected LocalDate period;

    /**
     * Constructor: creates a new Event
     * @param description Event description
     */
    public Event(String description, String period) throws DukeException {
        super(description);
        try {
            this.period = LocalDate.parse(period);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Please specify the date in this format:\n"
                                        + "  event [task description] /at [yyyy-mm-dd]");
        }
    }

    /**
     * saveFormat: creates a formatted representation of an Event to be saved
     * @return representation of an Event to be saved
     */
    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + period;
    }

    /**
     * toString: creates a String representation of an Event
     * @return String representation of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
