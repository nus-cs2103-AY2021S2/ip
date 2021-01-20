public class Event extends Task {
    protected String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getType(), getStatusIcon(), getDescription(), period);
    }
}
