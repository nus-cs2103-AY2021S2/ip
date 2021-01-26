package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private final LocalDateTime timeWindow;

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

    private String dateToString(LocalDateTime dateObject) {
        return DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a").format(dateObject);
    }
}
