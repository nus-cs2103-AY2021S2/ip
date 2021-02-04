package Tasks;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private String getByString() {
        return "(by: " + this.by + ")";
    }

    public String getBy() {
        return this.by;
    }

    public String getStatusString() {
        return "[D]" + super.getStatusString() + " " + this.getByString();
    }
}
