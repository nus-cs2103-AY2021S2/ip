public class EventTask extends Task {
    protected String date;

    public EventTask(String description, String date) {
        super(description, 'E');
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}
