package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(final String desc, final LocalDateTime date) {
        super(desc);
        this.deadline = date;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("dd LLL uu hhmma"))
        );
    }
}
