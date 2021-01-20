public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String taskDescription, String deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.taskType = 'D';
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[%c] [X] %s (by: %s)", this.taskType, this.taskDescription, this.deadline);
        } else {
            return String.format("[%c] [ ] %s (by: %s)", this.taskType, this.taskDescription, this.deadline);
        }
    }
}
