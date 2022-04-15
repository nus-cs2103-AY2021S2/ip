import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which is an event.
 */
public class Event extends Task {
    private String name;
    private LocalDate duration;
    private boolean isDone;

    /**
     * Constructor for Event which has not been done.
     * @param name Name of task.
     * @param duration Date of task in String.
     */
    Event(String name, String duration) {
        this.name = name;
        this.duration = LocalDate.parse(duration);
        this.isDone = false;
    }

    /**
     * Constructor for Event.
     * @param name Name of task.
     * @param duration Date of task in String.
     * @param done Indicates if task has been done.
     */
    Event(String name, String duration, boolean done) {
        this.name = name;
        this.duration = LocalDate.parse(duration);
        this.isDone = done;
    }

    /**
     * Constructor for Event.
     * @param name Name of task.
     * @param duration Date of task in LocalDate.
     * @param done Indicates if task has been done.
     */
    Event(String name, LocalDate duration, boolean done) {
        this.name = name;
        this.duration = duration;
        this.isDone = done;
    }

    /**
     * Marks task as done.
     * @return Completed task.
     */
    @Override
    Task done() {
        return new Event(this.name, this.duration.toString(), true);
    }

    /**
     * Sets the duration to a new duration.
     * @param date New duration.
     * @return Event with new duration.
     */
    @Override
    Task set(String date) {
        return new Event(this.name, date, this.isDone);
    }

    /**
     * Checks the equivalence of Event this and Object obj.
     * If obj is an instance of the Event class and all attributes are equivalent,
     * it is equivalent to this.
     * @param obj the object which will be compared to this.
     * @return Indication of whether obj is equivalent to this.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Event) {
            Event event = (Event) obj;
            return event.name.equals(this.name)
                    && (event.duration.equals(this.duration))
                            && (event.isDone == this.isDone);
        }
        return false;
    }

    /**
     * Represents an Event instance in String.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[E][X] %s (at: %s)",
                                 this.name,
                                 this.duration.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
        return String.format("[E][ ] %s (at: %s)",
                             this.name,
                             this.duration.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
