package percy.task;

public class Deadline extends Task {
    public static final String PREFIX = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: " + by + ")";
    }

    public String formatToFile() {
        return PREFIX + " | " + super.formatToFile() + " | "  + this.by;
    }
}
