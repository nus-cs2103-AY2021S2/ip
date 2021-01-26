public class Event extends Task{
    Date date;
    String duration;

    public Event(String description, Date date, String duration) {
        super(description);
        this.date = date;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toFormattedString() + " " + duration + ")";
    }
}
