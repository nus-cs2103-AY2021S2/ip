package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;

    /**
     * Initialises a new Event.
     *
     * @param taskName Description of task.
     * @param dateTime Date and Time.
     */
    public Event(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    /**
     * Returns a the task type.
     *
     * @return a String of the type.
     */
    @Override
    public String getTaskType() {
        return "Event";
    }

    /**
     * Returns a string of the LocalDateTime.
     *
     * @return a String of the date time.
     */
    public String getDateTime() {
        return dateTime.format(
                DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return this.dateTime;
    }
    /**
     * Returns a string of description to be saved in the myDuke.txt.
     *
     * @return A String.
     */
    @Override
    public String toSave() {
        return "E / " + super.isDoneString
                + super.taskName + " / " + dateTime;
    }

    /**
     * Returns a string of description to be printed out.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + getDateTime() + ")";
    }
}
