package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private final LocalDateTime deadline;

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
        return DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a").format(dateObject);
    }
}
