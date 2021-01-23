public class DeadlineTask extends Task {
    private static final String IDENTIFIER = "D";

    private String deadline;

    public DeadlineTask(String name, String deadline) {
        super(IDENTIFIER, name);
        this.deadline = deadline;
    }

    public DeadlineTask(String name, boolean isCompleted, String deadline) {
        super(IDENTIFIER, name, isCompleted);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
