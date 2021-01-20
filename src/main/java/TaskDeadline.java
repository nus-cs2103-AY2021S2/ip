public class TaskDeadline extends Task {

    private final String deadline;

    public TaskDeadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public TaskDeadline(String name, boolean done, String deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    public TaskDeadline setDone(boolean done) {
        return new TaskDeadline(this.name, done, this.deadline);
    }

    @Override
    protected TaskDeadline clone() {
        return new TaskDeadline(this.name, this.done, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
