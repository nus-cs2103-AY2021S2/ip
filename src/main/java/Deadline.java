import java.time.LocalDateTime;

public class Deadline extends Task{
    private static final char SYMBOL = 'D';
    private LocalDateTime deadline;

    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s (by: %s)", SYMBOL, super.toString(), super.format(this.deadline));
    }

    @Override
    public String save() {
        return String.format("%c,%s,%s\n", SYMBOL, super.save(), super.saveFormat(this.deadline));
    }
}
