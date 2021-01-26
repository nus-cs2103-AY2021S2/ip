public class Event extends Task{
    String dateAndDuration;

    public Event(String description, String dateAndDuration, String type) {
        super(description, type);
        this.dateAndDuration = dateAndDuration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndDuration + ")";
    }
}
