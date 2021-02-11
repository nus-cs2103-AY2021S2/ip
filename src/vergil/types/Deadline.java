package vergil.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {
    /**
     * Constructs a deadline task.
     * @param description a description of the deadline task.
     * @param time the time the task is due.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, time);
    }

    /**
     * Constructs a deadline task.
     * @param description a description of the task.
     * @param time the time the task is due.
     * @param isDone the completion status of the task.
     */
    public Deadline(String description, LocalDateTime time, boolean isDone) {
        super(description, time, isDone);
    }

    @Override
    public String getSaveString() {
        return String.format(
                "d::%s::%s",
                super.getSaveString(),
                getTime()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                getTime().format(DateTimeFormatter.ofPattern("d MMM y @ h:mm a")));
    }
}
