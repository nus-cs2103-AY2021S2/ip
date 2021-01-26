package duke.tasks;

public class Deadline extends Task{
    protected String by;

    public Deadline(String taskName, boolean isCompleted, String by) {
        super(taskName, isCompleted);
        this.by = by;
    }
    public String toStorageString() {
        return "D || " + (this.isCompleted ? "1" : "0") + " || " + this.taskName + " || " + " || " + this.by + ")";

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

