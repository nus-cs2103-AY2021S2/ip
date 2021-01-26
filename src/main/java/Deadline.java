import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getType() {
        return "D";
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getType(), getStatusIcon(), getDescription(),
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
