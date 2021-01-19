public class Event extends Task{
    protected String time;
    Event(String msg, String time) {
        super(msg);
        this.time = time;
    }

    @Override
    public String toString() {
        if (this.isDone.equals(true)) {
            return "[E][X] " + msg + time;
        } else {
            return "[E][ ] " + msg + time;
        }
    }
}
