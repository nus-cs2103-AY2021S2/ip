package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
/**
 * Inherited from Task, used to store information related to tasks of type 'deadline'.
 *
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline class object.
     * @param description deadline description
     * @param by a string that represents timing to be completed by
     *           currently, only "yyyy-mm-dd" format is supported.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
            assert !by.isEmpty() : "deadline cannot be created without a by date!";
        } catch (DateTimeParseException e) {
            throw new DukeException("invalid deadline date given.");
        }
    }

    /**
     * Constructor for Deadline class object, used for storage.
     * @param description deadline description
     * @param isDone if the deadline is done
     * @param by a string that represents timing to be completed by
     *           currently, only "yyyy-mm-dd" format is supported.
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description);
        try {
            assert !by.isEmpty() : "deadline cannot be created without a by date!";
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("invalid deadline date given.");
        }
        this.isDone = isDone;
    }

    /**
     * Acts as a helper method to print out the details of the deadline.
     * @return the details of the deadline
     */
    @Override
    public String toString() {
        assert by != null : "deadline does not have an by date!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Helper method to get the details of the deadline to be stored.
     * @returnthe the details of the deadline to be stored
     */
    @Override
    public String infoToStore() {
        assert !description.isEmpty() : "deadline does not have a description!";
        assert by != null : "deadline does not have an by date!";

        String divider = " | ";
        return "D" + divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + by;
    }
}
