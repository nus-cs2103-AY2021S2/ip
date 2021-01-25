package tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "Deadline";
    }

    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        this.taskType = "Deadline";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
