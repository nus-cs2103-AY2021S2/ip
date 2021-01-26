import java.time.LocalDateTime;
public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (%s - %s)", super.toString(), super.format(this.start), super.format(this.end));
    }
}
