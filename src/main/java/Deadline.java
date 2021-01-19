public class Deadline extends Task{
    protected final String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

