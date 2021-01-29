public class Events extends Task {
    String time;

    public Events(boolean isDone, String eventName, String time) {

        super(isDone, eventName, "E");
        this.time = time;
    }

    @Override
    public String toString() {

        return "[E] " + super.toString() + " (at: " + time + ")";
    }
}
