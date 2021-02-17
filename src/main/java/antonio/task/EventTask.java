package antonio.task;

import java.time.LocalDate;

/**
 * Represents an event task.
 */
public class EventTask extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs an event task.
     * @param description Description of the event.
     * @param id ID of task
     * @param status Status of task completion.
     * @param startDate Starting date of the event.
     * @param startTime Starting time of the event.
     * @param endDate Ending date of the event.
     * @param endTime Ending time of the event.
     */
    public EventTask(String description, int id, int status, LocalDate startDate, String startTime,
                     LocalDate endDate, String endTime) {
        super(description, id);
        super.isDone = status > 0;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Serializes event duration into a string format for local storage.
     * @return Serialized event duration.
     */
    public String serializeEvent() {
        return startDate.toString() + " | " + startTime + " | " + endDate.toString() + " | " + endTime;
    }

    /**
     * Pads the time string with zeroes to a 24 hour format.
     * @param time to be padded to 24 hour format
     * @return The padded time string.
     */
    private String padTimeFormat(String time) {
        String padding;
        if (time.length() == 1) {
            padding = String.format("%03d", 0);
        } else if (time.length() == 2) {
            padding = String.format("%02d", 0);
        } else if (time.length() == 3) {
            padding = String.format("%01d", 0);
        } else {
            padding = "";
        }
        return padding + time;
    }

    @Override
    public String toString() {
        return "[E]" + super.checkBoxToString() + description + "\n(at: " + startDate.getDayOfMonth()
                + " " + startDate.getMonth() + " " + startDate.getYear() + " "
                + padTimeFormat(startTime) + "HRS to " + endDate.getDayOfMonth()
                + " " + endDate.getMonth() + " " + endDate.getYear() + " " + padTimeFormat(endTime) + "HRS" + ")";
    }
}
