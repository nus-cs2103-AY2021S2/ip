import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    /**
     * Stores the date this event will be due by.
     */
    protected LocalDate by;

    /**
     * Initializes a newly created deadline-task object with a description and the date.
     * @param description Description of the task
     * @param by Date of the task (yyyy-mm-dd)
     */

    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format: Please specify the date as yyyy-mm-dd :)");
        }
    }

    protected String saveTask() { return "D | " + super.saveTask() + " | " + this.by; }

    /**
     * Converts this object to a string that represents the deadline-task
     * @return A string representing whether the deadline-task is done and the deadline-task description with the date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
