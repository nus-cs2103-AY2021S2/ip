package tasks;

public class DeadlineTask extends Task {
    private String type;
    private String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.type = "[D]";
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.type + super.toString() + " (" + this.deadline + ")";
    }
}
