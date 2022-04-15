package ekud.task;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Event task that occurs at a specified date and time
 */
public class EventTask extends TaskWithDateTime {
    /**
     * Construct an Event task.
     *
     * @param description The description of the Event.
     * @param at          The date and time that the Event occurs.
     */
    public EventTask(String description, LocalDateTime at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }

    /**
     * @return String LinkedList containing {"E", isDone, description, dateTime}
     */
    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addFirst("E");
        return list;
    }
}
