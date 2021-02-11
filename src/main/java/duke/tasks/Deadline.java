package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class Deadline
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent a deadline in the task list
 */
public class Deadline extends Task {
    protected LocalDate period;

    /**
     * Constructor: creates a new Deadline
     * @param description Deadline description
     */
    public Deadline(String description, String period) throws DukeException {
        super(description);
        try {
            this.period = LocalDate.parse(period);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please specify the date in this format:\n"
                                        + "  deadline [task description] /at [yyyy-mm-dd]");
        }
    }

    /**
     * saveFormat: creates a formatted representation of a Deadline to be saved
     * @return representation of a Deadline to be saved
     */
    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + period;
    }

    /**
     * toString: creates a String representation of a Deadline
     * @return String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}