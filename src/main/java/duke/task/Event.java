package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class which creates an event task.
 */
public class Event extends Task {
    private LocalDateTime date;
    private String taskType;

    /**
     * Creates Event task which keeps track of task details and timings.
     *
     * @param description description of the task to be saved
     * @param at timing of the event
     * */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.date = at;
        this.taskType = "event";
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
        String toPrint = "[E]" + super.toString() + " (at: "
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
     * Returns details of the event task.
     *
     * @return details of the task
     */
    @Override
    public String getTaskDetails() {
        return super.toString();
    }
}
