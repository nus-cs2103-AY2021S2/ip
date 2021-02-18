package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class Event represents an event that will be taken note by Danh's Duke
 * <p>
 * Event has 2 main components:
 * The event name (description): taskName
 * The time of event: eTime
 */
class Event extends Task {
    private final LocalDateTime eventTime;

    /**
     * Returns an event with specified name and time
     *
     * @param taskName  The event name (description)
     * @param eventTime The time of event
     */
    public Event(String taskName, LocalDateTime eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    /**
     * Returns a String, which is the expression of an Event.
     *
     * @return Event expression.
     */
    @Override
    public String printTask() {
        String ans;
        if (this.isTaskDone()) {
            ans = "[E][X] " + this.getTaskName() + " (at: "
                    + this.eventTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else if (!this.isTaskDone()) {
            ans = "[E][  ] " + this.getTaskName() + " (at: "
                    + this.eventTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else {
            ans = "Error";
        }
        assert (!ans.equals("Error")) : "The task should be either done or not";
        return ans;
    }

    @Override
    public boolean isTaskDone() {
        return super.isTaskDone();
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }

    /**
     * Returns the event time of this Event object in the correct format.
     *
     * @return the time of this Event object
     */
    public LocalDateTime getEventTime() {
        return eventTime;
    }
}

