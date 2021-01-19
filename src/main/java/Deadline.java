public class Deadline extends Task{
    protected String by;

    Deadline(String msg, String by) {
        super(msg);
        this.by = by;
    }

    Deadline(String msg, Boolean isDone) {
        super(msg, isDone);
    }

    @Override
    public Deadline setDone() {
        return new Deadline(this.msg, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
