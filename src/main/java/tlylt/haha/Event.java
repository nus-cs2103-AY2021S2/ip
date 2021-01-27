package tlylt.haha;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a Event task.
 */
public class Event extends Task {
    /**
     * Constructs an Event task.
     *
     * @param isDone      Whether task is set as completed.
     * @param description Details of task.
     */
    public Event(boolean isDone, String description) {
        super("E", isDone, description, Parser.getDate(description), Parser.getTime(description));
    }

    /**
     * Returns string representation of task.
     *
     * @return String task representation.
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + modifiedDescription("at");
    }
}
