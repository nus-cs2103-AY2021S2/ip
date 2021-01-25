import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {

    private LocalDateTime deadline;

    public DeadlineTask(String taskDescription, LocalDateTime deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.taskType = 'D';
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[%c] [X] %s (by: %s)", this.taskType, this.taskDescription,
                    this.dateToString(this.deadline));
        } else {
            return String.format("[%c] [ ] %s (by: %s)", this.taskType, this.taskDescription,
                    this.dateToString(this.deadline));
        }
    }

    private String dateToString(LocalDateTime dateObject) {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(dateObject);
    }
}
