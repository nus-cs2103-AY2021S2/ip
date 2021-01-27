package task;

public class Todo extends Task {

    private final String TYPE_ICON = "[T]";
    private final String ICON = "T";
    private final String DELIMITER = "|";

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
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
        return result;
    }

}
