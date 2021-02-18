import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event-task object that inherits from a task object.
 * Contains a description, status on whether the event is done and date of event.
 */
public class Event extends Task {

    /** Stores the date this event will be at. */
    protected LocalDate at;

    /**
     * Initializes a newly created event-task object with a description and the date.
     *
     * @param description Description of the task.
     * @param isDone Whether or not the task is done.
     * @param at Date of the task (yyyy-mm-dd).
     * @throws DukeException Throws error if the date is in the wrong format.
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format: Please specify the date as yyyy-mm-dd :)");
        }
    }

    /**
     * Returns date of event.
     *
     * @return LocalDate representing date of the event.
     */
    protected LocalDate getDate() {
        return this.at;
    }

    /**
     * Returns string object that formats the event-task to be saved.
     * Format: (Date format: d MMM yyyy)
     * <p> Event-task that is done: E | 1 | &lt;task_description&lt; | &lt;date&lt; </p>
     * <p> Event-task that is not done: E | 0 | &lt;task_description&lt; &lt;date&lt; </p>
     *
     * @return String representing event-task in format to be saved into txt.
     */
    protected String saveTask() {
        return "E | " + super.saveTask() + " | " + this.at;
    }

    /**
     * Converts this object to a string that represents the event-task.
     *
     * @return String representing whether the event-task is done and the event-task description with the date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
