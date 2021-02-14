package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private final String TYPE_ICON = "[D]";
    private final String ICON = "D";
    private final String DELIMITER = "|";
    private final String NULL = "NULL";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    private LocalDateTime time;

    public Deadlines(String description, String by, String tag) {
        super(description, tag);

        System.out.println(description);
        System.out.println(by);

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
            result = String.format("%s (by: %s)", result, this.time.format(formatter));
        }

        if (tag != null) {
            result = String.format("%s (tag: %s)", result, this.time.format(formatter));
        }

        return result;

    }
    /**
     * Converts the object into a String representation for storage
     *
     * @return  String represtentation
     */
    @Override
    public String tokenize() {

        String isDoneString = isDone ? "1" : "0";

        String result = ICON + DELIMITER + isDoneString + DELIMITER + this.description;

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
