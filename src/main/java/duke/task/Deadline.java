package duke.task;
import java.time.LocalDateTime;
/**
 * Represents a deadline task as a special case of task. A <code>deadline</code> object has three
 * fields, which are the task name, due time and done-status. e.g., <code>go to school, 2020-01-01 19:00, false</code>
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    /**
     * Constructor for Deadline object
     *
     * @param description The name of the deadline task.
     * @param by The deadline time of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = stringToDatetime(by);
    }
    /**
     * Constructor for Deadline object
     *
     * @param description The name of the deadline task.
     * @param by The deadline time of the deadline task in LocalDateTime type.
     * @param status The done-status of the deadline task.
     * @param priority The priority of the deadline task.
     */
    public Deadline(String description, LocalDateTime by, boolean status, int priority) {
        super(description, status, priority);
        this.by = by;
    }

    /**
     * Constructor for Deadline object
     *
     * @param description The name of the deadline task.
     * @param by The deadline time of the deadline task in String type.
     * @param status The done-status of the deadline task.
     * @param priority The priority of the deadline task.
     */
    public Deadline(String description, String by, boolean status, int priority) {
        super(description, status, priority);
        this.by = stringToDatetime(by);
    }

    /**
     * Constructor for Deadline object
     *
     * @param description The name of the deadline task.
     * @param by The deadline time of the deadline task in LocalDateTime type.
     * @param status The done-status of the deadline task.
     */
    public Deadline(String description, LocalDateTime by, boolean status) {
        super(description, status);
        this.by = by;
    }


    private LocalDateTime stringToDatetime(String by) {
        return LocalDateTime.parse(by, DF_INPUT);
    }

    private String datetimeToString(LocalDateTime by) {
        return DF_OUTPUT.format(by);
    }



    /**
     * Gets the deadline time as a LocalDateTime object.
     * @return the the deadline time as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return by;
    }


    /**
     * Gets the task name for a deadline object.
     * @return A String object that represent the task name, including information
     * about the task type, name and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.getTaskName() + " (by: " + datetimeToString(by) + ")";
    }
}
