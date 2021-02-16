import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate time;

    /**
     * Initialises a newly created Event object
     * so that it represents a Event task
     * with the name and time according to its supplied arguments.
     * Initialises the Event task isDone status to false.
     *
     * @param name the description of the Event task.
     * @param time LocalDate object that represents
     *            the time this Event is happening.
     * @param priority a String representing the priority of the task.
     */
    public Event(String name, String time, String priority) {
        super(name, priority);
        this.time = LocalDate.parse(time);
    }

    /**
     * Initialises a newly created Event object
     * so that it represents a Event task
     * with the name, isDone and time according to its supplied arguments.
     *
     * @param name the description of the Event task.
     * @param isDone the status of the Event task.
     * @param time LocalDate object that represents
     *            the time this Event is happening.
     * @param priority a String representing the priority of the task.
     */
    public Event(String name, boolean isDone, String time, String priority) {
        super(name, isDone, priority);
        this.time = LocalDate.parse(time);
    }

    /**
     * Creates a String representation of Event object.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("dd MMM yyy")));
    }

    LocalDate getTime() {
        return this.time;
    }
}
