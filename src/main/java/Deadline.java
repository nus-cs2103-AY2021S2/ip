public class Deadline extends Task{
    protected String by;

    Deadline(String msg, String by) {
        super(msg);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
