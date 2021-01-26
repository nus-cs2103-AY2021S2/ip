import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate date;

    Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getInitial() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
