public class Event extends Task {
    protected String day;
    protected String time;

    public Event(String description, String day, String time) {
        super(description);
        this.day = day;
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + " " + "(at: " + this.day + " " + this.time + ")";
    }
}
