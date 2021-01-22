public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String taskDescription, String deadline, int isDone) {
        super(taskDescription, "D", isDone);
        this.deadline = deadline;
    }

    @Override
    public String saveTask() {
        return String.format("%s /by %s", super.saveTask(), this.deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }
}
