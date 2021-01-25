public class Event extends Task{
    protected String by;
    protected char type;
    public Event(String task, String by) {
        super(task);
        this.by = by;
        this.type = 'E';
    }

    public Event(String task, boolean isDone, String by) {
        super(task, isDone);
        this.type = 'E';
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + type + "]"  + super.toString() + "(at:" + by + ")";
    }
}
