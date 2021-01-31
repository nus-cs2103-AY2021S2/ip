import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements DueDate {
    protected LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.time);
    }

    @Override
    public String getDueDate() {
        return this.time.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + time.format(formatter) + ")";
    }
}