package task;

public class Events extends Task {

    private final String typeIcon = "[E]";
    private final String icon = "E";
    private final String delimiter = "|";
    private final String nullString = "NULL";

    private String at;

    /**
     * Events constructor.
     *
     * @param description
     * @param at
     * @param tag
     * @return events object
     */
    public Events(String description, String at, String tag) {
        super(description, tag);
        this.at = at;
    }
    /**
     * Overloaded events constructor.
     *
     * @param isDone
     * @param description
     * @param at
     * @param tag
     * @return events object
     */
    public Events(boolean isDone, String description, String at, String tag) {
        super(description, tag);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return this.typeIcon;
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
     * @return  String representation
     */
    @Override
    public String tokenize() {
        String isDoneString = isDone ? "1" : "0";
        String result = icon + delimiter + isDoneString
                + delimiter + this.description;

        if (at == null) {
            result += delimiter + nullString;
        } else {
            result += delimiter + this.at;
        }

        if (tag == null) {
            result += delimiter + nullString;
        } else {
            result += delimiter + this.tag;
        }

        return result;
    }

    /**
     *
     * @return the description for the event task
     */
    @Override
    public String toString() {
        return getDescription();
    }

}
