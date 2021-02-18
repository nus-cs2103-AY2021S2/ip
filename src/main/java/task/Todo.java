package task;

/**
 * Class inherits from task.
 */
public class Todo extends Task {

    private final String typeIcon = "[T]";
    private final String icon = "T";
    private final String delimiter = "|";
    private final String nullString = "NULL";

    /**
     * Constructor for todo.
     * @param description
     * @param tag
     */
    public Todo(String description, String tag) {
        super(description, tag);
    }

    /**
     * Overloaded constructor with additional tag parameter.
     * @param isDone
     * @param description
     * @param tag
     */
    public Todo(boolean isDone, String description, String tag) {
        super(description, tag);
        this.isDone = isDone;
    }

    /**
     * Returns the type icon specific to todo.
     *
     * @return [T]
     */
    @Override
    public String getTypeIcon() {
        return this.typeIcon;
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
     * Converts the object into a String representation for storage.
     *
     * @return  String represtentation
     */
    @Override
    public String tokenize() {
        String isDoneString = isDone ? "1" : "0";
        String result = icon + delimiter + isDoneString + delimiter
                + this.description;

        if (tag == null) {
            result += delimiter + nullString;
        } else {
            result += delimiter + tag;
        }

        return result;
    }

}
