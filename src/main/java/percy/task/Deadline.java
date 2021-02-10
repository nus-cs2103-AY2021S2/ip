package percy.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final String PREFIX = "D";
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs deadline task.
     * @param description description of deadline.
     * @param date date of deadline.
     * @param time time of deadline.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Formats the deadline entry to be suitable for storage.
     * @return String to be stored.
     */
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
