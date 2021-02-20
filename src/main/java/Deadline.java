package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final String by;
    private final LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        date = parseStringToDateTime(by);
    }

    private LocalDate parseStringToDateTime(String by) {
        if (by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return LocalDate.parse(by);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        String deadline;
        if (date != null) {
            DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            deadline = date.format(myFormatter);
        } else {
            deadline = by;
        }
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String getTaskCommand() {
        return "deadline " + getDescription() + " /by " + by;
    }
}
