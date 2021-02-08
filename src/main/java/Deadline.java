public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.doneToString() + " (by: " + by + ")";
    }

    public String getBy() {
        return this.by;
    }

}
