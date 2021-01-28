package ssagit;

import java.util.Date;

public class DeadlineTask extends Task{
    String time;
    Date deadline;

    public DeadlineTask(String taskName, boolean isDone, Date deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (isDone) return "[D][X] " + taskName + " (by: " + time + ")";
        return "[D][ ] " + taskName + " (by: " + deadline.toString() + ")";
    }
}
