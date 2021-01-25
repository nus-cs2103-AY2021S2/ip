public class Deadline extends Task {
    protected String by;

    public Deadline(int isDone, String description, String by) {
        super('D', isDone, description);
        this.by = by;
    }

    @Override
    public String getFileString() {
        return super.getFileString() + " // " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
