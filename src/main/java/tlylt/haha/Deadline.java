package tlylt.haha;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a deadline task.
 */
public class Deadline extends Task {
    /**
     * Constructs a Deadline task.
     *
     * @param isDone      Whether task is set as completed.
     * @param description Details of task.
     */
    public Deadline(boolean isDone, String description) {
        super("D", isDone, description, Parser.getDate(description), Parser.getTime(description));
    }

    /**
     * Returns string representation of task.
     *
     * @return String task representation.
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + modifiedDescription("by");
    }
}
