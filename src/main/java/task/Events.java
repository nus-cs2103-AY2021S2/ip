package task;

public class Events extends Task {
    private final String TYPE_ICON = "[E]";
    private final String ICON = "E";
    private final String DELIMITER = "|";

    private String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public Events(boolean isDone, String description, String at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

    @Override
    public String getDescription() {
        if (at == null) {
            return this.description;
        } else {
            return String.format("%s (at: %s)", this.description, this.at);
        }
    }

    @Override
    public String tokenize() {
        String isDoneString = isDone ? "1" : "0";

        String result;

        if (at == null) {
            result = ICON + DELIMITER + isDoneString + DELIMITER + this.description;
        } else {
            result = ICON + DELIMITER + isDoneString + DELIMITER + this.description + DELIMITER + this.at;
        }
        return result;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
