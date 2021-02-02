import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline-task object that inherits from a task object.
 * Contains a description, status on whether the task is done and deadline of task.
 */
public class Deadline extends Task {

    /** Stores the date this event will be due by. */
    protected LocalDate by;

    /**
     * Initializes a newly created deadline-task object with a description and the date.
     *
     * @param description Description of the task.
     * @param isDone Whether or not the task is done.
     * @param by Date of the task (yyyy-mm-dd).
     * @throws DukeException Throws error if the date is in the wrong format.
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format: Please specify the date as yyyy-mm-dd :)");
        }
    }

    /**
     * Returns string object that formats the deadline-task to be saved.
     * Format: (Date format: d MMM yyyy)
     * <p> Deadline-task that is done: D | 1 | &lt;task_description&lt; | &lt;date&lt; </p>
     * <p> Deadline-task that is not done: D | 0 | &lt;task_description&lt; &lt;date&lt; </p>
     *
     * @return String representing deadline-task in format to be saved into txt.
     */
    protected String saveTask() {
        return "D | " + super.saveTask() + " | " + this.by;
    }

    /**
     * Converts this object to a string that represents the deadline-task.
     *
     * @return String representing whether the deadline-task is done and the deadline-task description with the date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
