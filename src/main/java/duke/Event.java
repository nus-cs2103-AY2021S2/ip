package duke;
import java.time.LocalDate;

/**
 * This class represents an event Task.
 */
class Event extends Task {
    // The date of the event.
    private final LocalDate date;

    /**
     * Creates a new event task object.
     *
     * @param taskName The name of the event
     * @param date The date in which the event is held on
     */
    public Event(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Overloaded constructor to create an event task object. It accepts one extra argument
     * to determine if the event is already done.
     *
     * @param taskName The name of the event
     * @param isDone Whether the event is already over
     * @param date The date of the event
     */
    public Event(String taskName, boolean isDone, LocalDate date) {
        super(taskName, isDone);
        this.date = date;
    }

    /**
     * Getter method to get the date in which the event is held on.
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
        String type = "[E]";
        String isCompleted;
        if (this.isDone) {
            isCompleted = "[X]";
        } else {
            isCompleted = "[ ]";
        }
        assert (this.taskName.equals("")) : "Name of event cannot be empty";
        return type + isCompleted + " " + this.taskName + " (by: " + Task.printDate(date) + ")";
    }
}
