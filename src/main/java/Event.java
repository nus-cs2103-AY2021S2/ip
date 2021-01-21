public class Event extends Task {
    private String time;

    public Event(String message, String time) {
        super("E", message);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
