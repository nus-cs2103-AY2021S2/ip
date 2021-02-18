package task;

public class Events extends Task {
    /**
     * Events represented by typeicon of [E].
     */
    private final String TYPE_ICON = "[E]";
    /**
     * Represented by icon E.
     */
    private final String ICON = "E";
    /**
     * To partition between the icons.
     */
    private final String DELIMITER = "|";
    /**
     * A null string.
     */
    private final String NULL = "NULL";

    private String at;

    public Events(String description, String at, String tag) {
        super(description, tag);
        this.at = at;
    }

    public Events(boolean isDone, String description, String at, String tag) {
        super(description, tag);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

    @Override
    public String getDescription() {
        String result = description;

        if (at != null) {
            result = String.format("%s (at: %s) ", result, this.at);
        }

        if (tag != null) {
            result = String.format("%s (tag: %s) ", result, this.tag);
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

        if (at == null) {
            result += DELIMITER + NULL;
        } else {
            result += DELIMITER + this.at;
        }

        if (tag == null) {
            result += DELIMITER + NULL;
        } else {
            result += DELIMITER + this.tag;
        }

        return result;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
