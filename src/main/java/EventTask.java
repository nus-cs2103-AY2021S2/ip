public class EventTask extends Task {

    protected String eventDate;

    public EventTask(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.description
                + " (at: " + this.eventDate + ")";
    }
}