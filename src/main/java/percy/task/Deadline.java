package percy.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final String PREFIX = "D";
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String formatToFile() {
        return PREFIX + " | " + super.formatToFile() + " | "
                + date + " "
                + time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }
}
