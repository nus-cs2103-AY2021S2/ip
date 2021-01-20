public class DeadlineTask extends Task {

    protected String deadline;

    public DeadlineTask(String description, String deadlline) {
        super(description);
        this.deadline = deadlline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline + ")";
    }
}
