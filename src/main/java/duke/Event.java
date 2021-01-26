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
public class Event extends Task {
    public LocalDateTime eTime;

    /**
     * Returns an event with specified name and time
     * @param taskName The event name (description)
     * @param eTime The time of event
     */
    public Event(String taskName, LocalDateTime eTime) {
        super(taskName);
        this.eTime = eTime;
    }

    /**
     * Returns a String, which is the expression of an Event.
     *
     * @return Event expression.
     */
    @Override
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[E][X] " + this.taskName + " (at: "
                    + this.eTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        } else {
            ans = "[E][ ] " + this.taskName + " (at: "
                    + this.eTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        }
        return ans;
    }
}

