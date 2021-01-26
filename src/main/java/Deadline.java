import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate time;

    public Deadline(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
    }

    public Deadline(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("dd MMM yyy")));
    }
}
