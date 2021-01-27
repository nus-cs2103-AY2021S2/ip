public class Deadlines extends Task {
    private final String TYPE_ICON = "[D]";
    private final String ICON = "D";
    private final String DELIMITER = "|";

    private String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

    @Override
    public String getDescription() {
        if (by == null) {
            return this.description;
        } else {
            return String.format("%s (by: %s)", this.description, this.by);
        }
    }

    @Override
    public String tokenize() {

        String isDoneString = isDone? "1" : "0";

        String result;

        if (by == null) {
            result = ICON + DELIMITER + isDoneString + DELIMITER + this.description;
        } else {
            result = ICON + DELIMITER + isDoneString + DELIMITER + this.description + DELIMITER + this.by;
        }
        return result;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
