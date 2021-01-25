package ekud.task;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * A deadline task that requires a specified date and time set as its deadline
 */
public class Deadline extends TaskWithDateTime {
    /**
     * Construct a Deadline task.
     *
     * @param description The description of the Deadline.
     * @param by          The completion date and time information.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }

    /**
     * @return String LinkedList containing {"D", isDone, description, dateTime}.
     */
    @Override
    public LinkedList<String> export() {
        LinkedList<String> list = super.export();
        list.addFirst("D");
        return list;
    }
}
