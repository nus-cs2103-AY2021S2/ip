import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected  String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = parseDate(by);
    }

    private LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }
    @Override
    public String save() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }
}
