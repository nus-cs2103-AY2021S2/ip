import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate date;

    Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getInitial() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + date + ")";
    }
}
