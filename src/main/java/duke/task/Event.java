package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that happens on a specific date and time.
 */
public class Event extends Task {

    protected LocalDate date;
    protected String duration;

    /**
     * constructor of Event task.
     *
     * @param description description of task.
     * @param isCompleted boolean to specify if the task has been completed.
     * @param time time the task occurs.
     */
    public Event(String description, boolean isCompleted, String time) {
        super(description, isCompleted);
        String[] dateDuration = time.split(" ");
        String eventDate = dateDuration[0];
        this.date = LocalDate.parse(eventDate);
        this.duration = dateDuration[1];
    }

    public LocalDate getTaskDate() {
        return this.date;
    }

    /**
     * Returns formatted string of the event task details to store in harddisk file.
     * @return Formatted string.
     */
    @Override
    public String getFormattedData() {
        return "E | " + super.getFormattedData() + " | " + date + " " + duration;
    }

    /**
     * Returns description of the event, status of the event as well as its date and time.
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + duration + ")";
    }
}
