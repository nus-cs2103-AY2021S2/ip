package antonio.task;

import java.time.LocalDate;

/**
 * Represents an event task.
 */
public class EventTask extends Task {

    private LocalDate eventDate;
    private String startTime;
    private String endTime;

    /**
     * Constructor for event task.
     * @param description Name of the command.
     * @param id ID of task
     * @param status Status of task completion.
     * @param eventDate Date of deadline for the task.
     * @param startTime Start time of deadline for the task.
     * @param endTime End time of deadline for the task.
     */
    public EventTask(String description, int id, int status, LocalDate eventDate, String startTime, String endTime) {
        super(description, id);
        super.isDone = status > 0;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Serializes event duration into a string format for local storage.
     * @return Serialized event duration.
     */
    public String serializeEvent() {
        return eventDate.toString() + " | " + startTime + " | " + endTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.checkBoxToString() + description + " (at: " + eventDate.getDayOfMonth()
                + " " + eventDate.getMonth() + " " + eventDate.getYear() + " "
                + startTime + "HRS to " + endTime + "HRS" + ")";
    }
}
