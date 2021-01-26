public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description, "D");
        this.time = time;
    }

    @Override
    public String serialize() {
        return String.format("%s|%b|%s|%s", getType(), isDone, getDescription(), time);
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getType(), getStatusIcon(), getDescription(), time);
    }
}
