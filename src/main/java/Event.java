public class Event extends Task {
    protected String period;

    public Event(String description, String period) {
        super(description, "E");
        this.period = period;
    }

    @Override
    public String serialize() {
        return String.format("%s|%b|%s|%s", getType(), isDone, getDescription(), period);
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", getType(), getStatusIcon(), getDescription(), period);
    }
}
