package vergil.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    /**
     * Constructs an event task.
     * @param description a description of the task.
     * @param time the time the task is scheduled to occur.
     */
    public Event(String description, LocalDateTime time) {
        super(description, time);
    }

    /**
     * Constructs an event task.
     * @param description a description of the task.
     * @param time the time the task is scheduled to occur.
     * @param isDone the completion status of the task.
     */
    public Event(String description, LocalDateTime time, boolean isDone) {
        super(description, time, isDone);
    }

    @Override
    public String getSaveString() {
        return String.format("e::%s::%s", super.getSaveString(), getTime());
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                getTime().format(DateTimeFormatter.ofPattern("d MMM y @ h:mm a")));
    }
}
