public class Event extends Task {

    protected String datetime;

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[R]" + super.toString() + " (at: " + datetime + ")";
    }
}