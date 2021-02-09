package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class to handle tasks that starts at a specific date/time and ends at a specific date/time
 */
public class Event extends Task {
    protected final LocalDate at;

    /**
     * Constructor for Event class
     *
     * @param description Details of the task
     * @param at Starts and ends at specific date/time to complete the task
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Displays simplified version of task type, description and timeframe of tasks in Duke.txt
     *
     * @return String format regarding the Event details
     */
    @Override
    public String formatTask() {
        return String.format("E | %s", super.formatTask());
    }

    /**
     * Displays task type, description and timeframe of tasks
     *
     * @return String format regarding the Event details
     */
    @Override
    public String toString() {
        String taskDescription = super.toString();
        String formattedDate = this.at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String taskMsg = "[E]" + taskDescription + " (at: " + formattedDate + ")";
        return taskMsg;
    }
}
