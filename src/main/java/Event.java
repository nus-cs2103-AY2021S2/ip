public class Event extends Task {
    String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toSaveFormat() {
        String status = super.isDone ? "1" : "0";
        return String.format("E|%s|%s\n", status, super.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.time);
    }
}
