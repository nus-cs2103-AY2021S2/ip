package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class to handle tasks that are need to be done before a specific date/time
 */
public class Deadline extends Task {
    protected final LocalDate by;

    /**
     * Constructor for DeadLine class
     *
     * @param description Details of the task
     * @param by Specific date/time to complete the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Displays simplified version of task type, description and deadline of tasks in Duke.txt
     *
     * @return String format regarding the task deadline information
     */
    @Override
    public String formatTask() {
        return String.format("D | %s", super.formatTask());
    }

    /**
     * Displays task type, description and deadline of task
     *
     * @return String format regarding the task deadline information
     */
    @Override
    public String toString() {
        String taskDescription = super.toString();
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String taskMsg = "[D]" + taskDescription + " (by: " + formattedDate + ")";
        return taskMsg;
    }
}
