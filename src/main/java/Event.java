import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    /**
     * Stores the date this event will be at.
     */
    protected LocalDate at;

    /**
     * Initializes a newly created event-task object with a description and the date.
     * @param description Description of the task
     * @param at Date of the task (yyyy-mm-dd)
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format: Please specify the date as yyyy-mm-dd :)");
        }
    }

    protected String saveTask() { return "E | " + super.saveTask() + " | " + this.at; }

    /**
     * Converts this object to a string that represents the event-task
     * @return A string representing whether the event-task is done and the event-task description with the date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
