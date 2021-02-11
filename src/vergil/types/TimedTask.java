package vergil.types;

import java.time.LocalDateTime;

public abstract class TimedTask extends Task {
    private LocalDateTime dateTime;

    public TimedTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public TimedTask(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public LocalDateTime getTime() {
        return dateTime;
    }
}
