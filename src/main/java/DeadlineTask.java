import java.time.LocalDate;

public class DeadlineTask extends Task {
    private LocalDate deadline;

    public DeadlineTask(String taskDescription, LocalDate deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
