package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private final LocalDateTime timeWindow;

    /**
     * Constructs new Event task with provided taskDescription and deadline
     * @param taskDescription description of task
     * @param timeWindow timewindow of the given task
     */
    public EventTask(String taskDescription, LocalDateTime timeWindow) {
        this.taskDescription = taskDescription;
        this.timeWindow = timeWindow;
        this.taskType = 'E';
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[%c] [X] %s (at: %s)", this.taskType, this.taskDescription,
                    this.dateToString(this.timeWindow));
        } else {
            return String.format("[%c] [ ] %s (at: %s)", this.taskType, this.taskDescription,
                    this.dateToString(this.timeWindow));
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
