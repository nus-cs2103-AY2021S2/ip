package task;

public class Todo extends Task {

    private final String TYPE_ICON = "[T]";
    private final String ICON = "T";
    private final String DELIMITER = "|";
    private final String NULL = "NULL";

    public Todo(String description, String tag) {
        super(description, tag);
    }

    public Todo(boolean isDone, String description, String tag) {
        super(description, tag);
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

    @Override
    public String getDescription() {
        if (tag == null) {
            return this.description;
        } else {
            return String.format("%s (tag: %s)", this.description, this.tag);
        }
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

        if (tag == null) {
            result += DELIMITER + NULL;
        } else {
            result += DELIMITER + tag;
        }

        return result;
    }

}
