import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    private LocalDate by;
    private char type;

    public Deadline(String task, String by) {
        super(task);
        by = by.replaceFirst(" ", "");
        this.by = LocalDate.parse(by);
        this.type = 'D';
    }

    public Deadline(String task, boolean isDone, String by) {
        super(task, isDone);
        this.type = 'D';
        by = by.replaceFirst(" ", "");
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[" + type + "]"  + super.toString()
                + "(by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getData() {
        return "[" + type + "]"  + super.toString()
                + " (by:" + by + ")";
    }
}
