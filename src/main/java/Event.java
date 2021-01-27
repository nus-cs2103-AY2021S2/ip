public class Event extends Task {
    protected String location;

    public Event(String description, String location) {
        super(description);
        this.location = location;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + location;
    }
}
