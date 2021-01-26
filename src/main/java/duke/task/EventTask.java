package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class encapsulates information and methods about a Event Task
 * and inherits functionality from the Task class.
 */
public class EventTask extends Task {

    private static final String SEPARATOR = "|";
    private LocalDateTime date;

    /**
     * Create and initialize a new Event Task.
     *
     * @param taskName Name of the new Event Task.
     * @param date Date of the new Event Task.
     */
    public EventTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Returns a string representation of a Event task for the user.
     * @return Type of task, status, followed by the name of Event task and date the task will occur.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a")) + ")";
    }

    /**
     * Returns a string representation in the format of how the Event task should be saved in the data file.
     * @return String for chatbot to save into a saved data file of tasks.
     */
    public String getSavingString() {
        return "EVENT" + super.getSavingString() + SEPARATOR
                + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
    }
}
