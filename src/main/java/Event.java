public class Event extends Task{
    protected String time;
    Event(String msg, String time) {
        super(msg);
        this.time = time;
    }

    Event(String msg, Boolean isDone) {
        super(msg, isDone);
    }

    @Override
    public Event setDone() {
        return new Event(this.msg, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
