public class Event extends Task{
    protected String by;
    public Event(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]"  + super.toString() + "(at:" + by + ")";
    }
}
