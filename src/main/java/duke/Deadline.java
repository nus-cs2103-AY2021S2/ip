package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("dd MMM YYYY"));
    }
}
