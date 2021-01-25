import java.time.LocalDate;

public class deadline extends Task {
    protected LocalDate date;

    deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate get_date() { return date; }

    @Override
    public String get_initial() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + date + ")";
    }
}
