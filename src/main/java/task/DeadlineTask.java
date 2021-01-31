package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private final LocalDateTime deadline;

    /**
     * Constructs new Deadline task with provided taskDescription and deadline
     * @param taskDescription description of task
     * @param deadline deadline of the given task
     */
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

    /**
     * Convert LocalDateTime object to a string representation
     * @param dateObject LocalDateTime object to be converted
     * @return string of formatted LocalDateTime object
     */
    private String dateToString(LocalDateTime dateObject) {
        return DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a").format(dateObject);
    }
}
