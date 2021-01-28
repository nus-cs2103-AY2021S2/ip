package ssagit;

import java.util.Date;

public class EventTask extends Task {
    String time;
    Date deadline;

    public EventTask(String taskName, boolean isDone, Date deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (isDone) return "[E][X] " + taskName + " (at: " + time + ")";
        return "[E][ ] " + taskName + " (at: " + deadline.toString() + ")";
    }
}