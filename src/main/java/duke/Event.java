package duke;
import java.time.LocalDate;

/**
 * This class represents an event Task.
 */
class Event extends Task {
    private final LocalDate date;

    /**
     * Creates a new event task object.
     *
     * @param taskName The name of the event
     * @param date The date of the event
     */
    public Event(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Overloaded constructor to create an event task object. It accepts one extra argument
     * to determine if the task is already done.
     *
     * @param taskName The name of the event
     * @param isDone Whether the event is already done
     * @param date The date of the event
     */
    public Event(String taskName, boolean isDone, LocalDate date) {
        super(taskName, isDone);
        this.date = date;
    }

    /**
     * Getter method to get the date in which the event is due.
     *
     * @return the date of the event
     */
    protected String getDate() {
        return Task.printDate(this.date);
    }

    /**
     * Prints the details of the event in a special format.
     *
     * @return the details of the event, such as the type, whether it is done and its date.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[E][X] " + this.taskName + " (at: " + Task.printDate(date) + ")";
        } else {
            return "[E][ ] " + this.taskName + " (at: " + Task.printDate(date) + ")";
        }
    }
}