public class EventsTask extends Task {
    protected String duration;

    public EventsTask(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (by: %s)",
                done == Status.DONE ? "X" : " ",
                name,
                duration);
    }
}
