import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    private char type;
    private LocalDate at;

    public Event(String task, String at) {
        super(task);
        at = at.replaceFirst(" ", "");
        this.at = LocalDate.parse(at);
        this.type = 'E';
    }

    public Event(String task, boolean isDone, String at) {
        super(task, isDone);
        this.type = 'E';
        at = at.replaceFirst(" ", "");
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString()
                + "(at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getData() {
        return "[" + type + "]" + super.toString()
                + " (at:" + at + ")";
    }
}
