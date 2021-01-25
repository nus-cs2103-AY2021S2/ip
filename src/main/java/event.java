import java.time.LocalDate;

public class event extends Task {
    protected LocalDate date;

    event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate get_date() { return date; }

    @Override
    public String get_initial() { return "E"; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
