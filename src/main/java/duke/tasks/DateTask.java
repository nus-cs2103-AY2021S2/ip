package duke.tasks;

import java.time.LocalDateTime;

public class DateTask extends Task {

    protected LocalDateTime datetime;

    public DateTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }
}
