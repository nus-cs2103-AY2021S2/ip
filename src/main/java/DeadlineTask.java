/**
 * Deadline task that need to be done before a specific date/time.
 */
public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String taskArgs) throws OwenException {
        super();

        String[] taskArgsSplit = taskArgs.split(" /by ", 2);

        if (taskArgsSplit.length < 2) {
            throw new OwenException("Deadline task must have a description and due date/time...");
        }

        this.description = taskArgsSplit[0];
        this.deadline = taskArgsSplit[1];
    }

    private DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public DeadlineTask markAsDone() {
        return new DeadlineTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        String taskFormat = "[%s][%s] %s (by: %s)";
        return String.format(
                taskFormat,
                this.getTypeIcon(),
                this.getStatusIcon(),
                this.description,
                this.deadline);
    }

    @Override
    protected String getTypeIcon() {
        return "D";
    }
}
