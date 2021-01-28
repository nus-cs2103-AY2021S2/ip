package ssagit;

public class DeadlineTask extends Task{
    String time;

    public DeadlineTask(String taskName, boolean isDone, String time) {
        super(taskName, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) return "[D][X] " + taskName + " (by: " + time + ")";
        return "[D][ ] " + taskName + " (by: " + time + ")";
    }
}
