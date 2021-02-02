package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /**
     * Creates a new event task
     * @param name event name
     * @param date event due date
     */
    public Event(String name, LocalDate date) {
        super(name, "Event coming right up!");
        this.date = date;
    }

    /**
     * Overloads the Event(String name, LocalDate date) method, with new status parameter.
     * This can be used if status is required to be defined.
     * @param name event name
     * @param date event date
     * @param status event status (done, not done)
     */
    public Event(String name, LocalDate date, Boolean status) {
        this(name, date);
        this.status = status;
    }

    /**
     * Returns a formatted string required for storing Event task to .txt file
     * @return formatted string of Task for file
     */
    @Override
    public String toFileString() {
        return "E," + (this.status ? "1" : "0") + "," + this.name + ","
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
