package ssagit;

public class EventTask extends Task {
    String time;

    public EventTask(String taskName, boolean isDone, String time) {
        super(taskName, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) return "[E][X] " + taskName + " (at: " + time + ")";
        return "[E][ ] " + taskName + " (at: " + time + ")";
    }
}