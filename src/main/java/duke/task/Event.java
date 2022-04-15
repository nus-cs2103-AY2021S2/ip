package duke.task;
import java.time.LocalDateTime;
/**
 * Represents a event task as a special case of task. A <code>event</code> object has three
 * fields, which are the task name, due time and done-status. e.g., <code>go to school, 2020-01-01 19:00, false</code>
 */
public class Event extends Task {
    protected LocalDateTime at;


    /**
     * Constructor for event object
     *
     * @param description The name of the event task.
     * @param at The deadline time of the event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = stringToDatetime(at);
    }


    /**
     * Constructor for event object
     *
     * @param description The name of the event task.
     * @param at The deadline time of the event task in String type.
     * @param status The done-status of the event task.
     * @param priority The priority of the event task.
     */
    public Event(String description, String at, boolean status, int priority) {
        super(description, status, priority);
        this.at = stringToDatetime(at);
    }

    /**
     * Constructor for event object
     *
     * @param description The name of the event task.
     * @param at The deadline time of the event task in LocalDateTime type.
     * @param status The done-status of the event task.
     * @param priority The priority of the event task.
     */
    public Event(String description, LocalDateTime at, boolean status, int priority) {
        super(description, status, priority);
        this.at = at;
    }


    /**
     * Constructor for event object
     *
     * @param description The name of the event task.
     * @param at The deadline time of the event task in LocalDateTime type.
     * @param status The done-status of the event task.
     */
    public Event(String description, LocalDateTime at, boolean status) {
        super(description, status);
        this.at = at;
    }

    private LocalDateTime stringToDatetime(String at) {
        return LocalDateTime.parse(at, DF_INPUT);
    }

    private String datetimeToString(LocalDateTime at) {
        return DF_OUTPUT.format(at);
    }


    /**
     * Gets the event time as a LocalDateTime object.
     * @return the the event time as a LocalDateTime object.
     */
    public LocalDateTime getAt() {
        return at;
    }


    /**
     * Gets the task name for a event object.
     * @return A String object that represent the task name, including information
     * about the task type, name and event time.
     */
    @Override
    public String toString() {
        return "[E]" + super.getTaskName() + " (at: " + datetimeToString(at) + ")";
    }
}


