package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Initializes a deadline with its description and time.
     *
     * @param description Description of the deadline.
     * @param by          Time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the deadline to a string which will be saved in a file.
     *
     * @return String representing the deadline in its save format.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0")
                + " | " + description + " | " + Parser.parseDateTimeToString(by);
    }

    /**
     * Converts the deadline to a string.
     *
     * @return String representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.parseDateTimeToString(by) + ")";
    }
}
