public class Deadline extends Task {
    protected String by;
    protected char type;
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
        this.type = 'D';
    }

    public Deadline(String task, boolean isDone, String by) {
        super(task, isDone);
        this.type = 'D';
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + type + "]"  + super.toString() + "(by:" + by + ")";
    }
}
