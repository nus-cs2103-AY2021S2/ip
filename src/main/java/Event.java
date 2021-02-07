public class Event extends Task {
    private String time;

    public Event(String message, String time) {
        super("E", message);
        this.time = time;
    }

    @Override
    public String toMemString() {
        return super.toMemString() + " | " + this.time;
    }

    @Override
    public String toString() {
        return super.toString() + this.time;
    }
}
