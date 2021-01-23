package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dueDates;
    public Deadline(String task, String dueDates) {
        super(task);
        this.dueDates = LocalDate.parse(dueDates);
    }

    public Deadline(String task, LocalDate dueDates) {
        super(task);
        this.dueDates = dueDates;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dueDates.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
