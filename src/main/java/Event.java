public class Event extends Task {
    public String date;

    Event(String description, String date) {
        super(description);
        this.date = date;
        isDone = false;
    }
    @Override
    public String getDate() {
        return " (at: " + date + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getDate() + "\n";
    }
}
