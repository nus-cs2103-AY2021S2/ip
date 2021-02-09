public class Event extends Task {

    protected String datetime;

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public String getDatetime() {
        return this.datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }
}