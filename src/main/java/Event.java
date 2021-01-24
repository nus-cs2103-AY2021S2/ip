public class Event extends Task {
    private final String date;

    Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    public String getDate() {
        return this.date;
    }
}
