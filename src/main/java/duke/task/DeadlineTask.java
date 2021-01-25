package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class encapsulates information and methods about a Deadline Task
 * and inherits functionality from the Task class.
 */
public class DeadlineTask extends Task {

    private static final String SEPARATOR = "|";
    private LocalDateTime date;

    /**
     * Create and initialize a new Deadline Task.
     *
     * @param taskName Name of the new Deadline Task.
     * @param date Date of the new Deadline Task.
     */
    public DeadlineTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Returns a string representation of a Deadline task for the user.
     * @return Type of task, status, followed by the name of Deadline task and date the task is due.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh.mm a")) + ")";
    }

    /**
     * Returns a string representation in the format of how the Deadline task should be saved in the data file.
     * @return String for chatbot to save into a saved data file of tasks.
     */
    public String getSavingString() {
        return "DEADLINE" + super.getSavingString() + SEPARATOR + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "\n";
    }
}
