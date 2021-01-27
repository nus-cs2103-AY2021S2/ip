import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    public String getDisplayBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Task.DATE_DISPLAY_FORMAT));
    }

    public String getSaveBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Event.DATE_SAVE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDisplayBy() + ")";
    }
}
