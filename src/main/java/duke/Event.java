package duke;
import java.time.LocalDate;

/**
 * This class represents an event Task.
 */
class Event extends Task {
    private final LocalDate dateOfEvent;

    /**
     * Creates a new event task object.
     *
     * @param taskName The name of the event task.
     * @param dateOfEvent The date on which the event is held on.
     */
    public Event(String taskName, LocalDate dateOfEvent) {
        super(taskName);
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * Overloaded constructor to create an event task object. It accepts one extra
     * argument to determine if the event is already over.
     *
     * @param taskName The name of the event task.
     * @param isDone Whether the event is already over.
     * @param dateOfEvent The date on which the event is held on.
     */
    public Event(String taskName, boolean isDone, LocalDate dateOfEvent) {
        super(taskName, isDone);
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * Getter method to get the date on which the event is held on.
     *
     * @return The date on which the event is held on.
     */
    public String getDate() {
        return Task.printDate(this.dateOfEvent);
    }

    /**
     * Prints the details of the event task.
     *
     * @return A string representation of the details of the event task.
     */
    @Override
    public String toString() {
        assert (this.taskName.equals("")) : "Name of event cannot be empty";
        String typeOfTask = "[E]";
        String isCompleted;
        if (this.isDone) {
            isCompleted = "[X]";
        } else {
            isCompleted = "[ ]";
        }
        String message = typeOfTask + isCompleted + " " + this.taskName;
        message += " (at: " + Task.printDate(dateOfEvent) + ")";
        return message;
    }
}
