public class Event extends Task {
    String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + "(at: " + this.eventTime + ")";
    }

    @Override
    public String saveStatus() {
        return "E" + super.saveStatus();
    }
}
