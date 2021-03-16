package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadlines extends Task {

    private final String typeIcon = "[D]";
    private final String icon = "D";
    private final String delimiter = "|";
    private final String nullString = "NULL";

    /**
     * Format of how date and time is displayed.
     */
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private LocalDateTime time;

    /**
     * Constructor of deadlines.
     *
     * @param description description of task
     * @param by time of task
     * @param tag tag of task
     */
    public Deadlines(String description, String by, String tag) {
        super(description, tag);

        if (by == null) {
            time = null;
        } else {
            try {
                time = LocalDateTime.parse(by, formatter);
            } catch (Exception e) {
                time = null;
            }
        }
    }

    /**
     * returns typeicon of deadline.
     *
     * @return [D]
     */
    @Override
    public String getTypeIcon() {
        return this.typeIcon;
    }

    /**
     * returns the description of the deadline.
     *
     * @return string of the deadline description
     */
    @Override
    public String getDescription() {
        String result = description;

        if (time != null) {
            result = String.format("%s (by: %s)", result,
                    this.time.format(formatter));
        }
        if (tag != null) {
            result = String.format("%s (tag: %s)", result, this.tag);
        }

        return result;

    }
    /**
     * Converts the object into a String representation for storage.
     *
     * @return  String representation of object
     */
    @Override
    public String tokenize() {

        String isDoneString = isDone ? "1" : "0";

        String result = icon + delimiter + isDoneString
                + delimiter + this.description;

        if (time == null) {
            result += delimiter + nullString;
        } else {
            result += delimiter + time;
        }

        if (tag == null) {
            result += delimiter + nullString;
        } else {
            result += delimiter + tag;
        }

        return result;
    }

    /**
     * returns the description of the deadline task.
     *
     * @return String of the deadline task
     */
    @Override
    public String toString() {
        return getDescription();
    }

}
