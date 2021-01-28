package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents any deadline specified with deadline description, status and due date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline class.
     * @param description Description of the deadline.
     * @param status To check if the deadline is done.
     * @param by Due date and time.
     */
    public Deadline(String description, int status, LocalDateTime by) {
        super(description,status);
        this.by = by;
    }

    /**
     * Converts deadline details into txt format for local storage.
     * @return The text stored in the local file.
     */
    @Override
    public String toTxt(){
        return "D " + super.toTxt() + " | " + by.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + "\n";
    }

    /**
     * Generates deadline details displayed on the user interface.
     * @return Message output for Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + ")";
    }
}
