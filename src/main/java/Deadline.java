public class Deadline extends Task{
    public static final String SHORT_HAND = "D";
    protected final String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + SHORT_HAND + "]" + super.toString() + " (by: " + this.by + ")";
    }
}

