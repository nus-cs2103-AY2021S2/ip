/**
 * Implementation for tasks with a given start-time and end-time.
 */

public class Event extends Task {
    private final String startAndEnd;
    public Event(String taskName, String startAndEnd) {
        super(taskName, "E");
        this.startAndEnd = startAndEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.startAndEnd + ")";
    }
}
