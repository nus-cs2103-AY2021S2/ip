public class Deadline extends Task{
    protected String by;
    Deadline(String task, String by) {
        super(task);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + this.by + ")";
    }
}
