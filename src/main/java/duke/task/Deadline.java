package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class which creates a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime date;
    private String taskType;

    /**
     * Creates Deadline task which keeps track of task details and deadlines.
     *
     * @param description description of the task to be saved
     * @param by deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.date = by;
        this.taskType = "deadline";
    }

    /**
     * Method to get the type of task
     *
     * @return type of task
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        String toPrint = "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
        assert !toPrint.isEmpty() : "Something should be printed.";
        return toPrint;
    }

    /**
     * Method to ammend task details
     *
     * @params newDetails to be updated
     */
    public void updateTaskDateAndTime(LocalDateTime newDateTime) {
        this.date = newDateTime;
    }

    /**
     * Returns details of the deadline task.
     *
     * @return details of the task
     */
    @Override
    public String getTaskDetails() {
        return super.toString();
    }
}
