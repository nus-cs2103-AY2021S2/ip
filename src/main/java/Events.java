public class Events extends Task {
    String time;

    public Events(String eventName, String time) {

        super(false, eventName);
        this.time = time;
    }

    @Override
    public String toString() {

        return "[E] " + super.toString() + "(at: " + time + ")";
    }
}
