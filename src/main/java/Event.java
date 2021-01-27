import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, true);
        this.start = start;
        this.end = end;
    }

    @Override
    public Event finishTask() {
        return new Event(description, start, end, true);
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s | %s-%s", super.getStatusIcon(),
                description, super.timeFormat(start) , super.timeFormat(end));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s - %s)", super.toString(),
                super.timeFormat(start) , super.timeFormat(end));
    }
}