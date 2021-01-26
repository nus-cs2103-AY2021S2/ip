import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description, "D");
        this.date = date;
    }

    @Override
    public String serialize() {
        return String.format("%s|%b|%s|%s", getType(), isDone, getDescription(),
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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
