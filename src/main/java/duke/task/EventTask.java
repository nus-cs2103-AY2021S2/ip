package duke.task;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private final LocalDateTime event;

    public EventTask(final String desc, final LocalDateTime date) {
        super(desc);
        this.event = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), event);
    }
}
