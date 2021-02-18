package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    /**
     *Type icon for deadlines task.
     */
    private final String TYPE_ICON = "[D]";
    /**
     * Icon is represented by D.
     */
    private final String ICON = "D";
    /**
     * To have a line between the details.
     */
    private final String DELIMITER = "|";
    /**
     * Null String.
     */
    private final String NULL = "NULL";
    /**
     * Format of how date and time is displayed.
     */
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    /**
     * To make time accessible outside of the method.
     */
    private LocalDateTime time;

    /**
     * Contructor of deadlines.
     *
     * @param description
     * @param by
     * @param tag
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
    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }
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
     * @return  String represtentation
     */
    @Override
    public String tokenize() {

        String isDoneString = isDone ? "1" : "0";

        String result = ICON + DELIMITER + isDoneString
                + DELIMITER + this.description;

        if (time == null) {
            result += DELIMITER + NULL;
        } else {
            result += DELIMITER + time;
        }

        if (tag == null) {
            result += DELIMITER + NULL;
        } else {
            result += DELIMITER + tag;
        }

        return result;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
