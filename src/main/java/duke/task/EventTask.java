package duke.task;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public EventTask(final String desc, final LocalDateTime start, final LocalDateTime end) {
        super(desc);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(), startDate, endDate);
    }
}
