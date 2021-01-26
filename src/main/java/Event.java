import java.time.LocalDateTime;
public class Event extends Task{
    private static final char SYMBOL = 'E';
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s (%s - %s)", SYMBOL, super.toString(),
                    super.format(this.start), super.format(this.end));
    }

    @Override
    public String save() {
        return String.format("%c,%s,%s,%s\n", SYMBOL, super.save(),
                    super.saveFormat(this.start), super.saveFormat(this.end));
    }
}
