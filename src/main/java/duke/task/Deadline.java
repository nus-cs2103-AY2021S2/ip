package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends ListItem {
    private final String date;
    private LocalDate parsedDate;

    public Deadline(String task, String inputDate) {
        super(task);
        this.date = inputDate;
        this.parsedDate = parseDate(inputDate);
    }

    public Deadline(String task, String inputDate, boolean isDone) {
        super(task, isDone);
        this.date = inputDate;
        this.parsedDate = parseDate(inputDate);
    }

    @Override
    public ListItem markAsDone() {
        return new Deadline(super.getTask(), (parsedDate == null ? this.date : parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))), true);
    }

    @Override
    public String toString() {
        return "[D]" + (super.getDone() == true ? "[X] " : "[ ] ") + super.getTask() + " (by: " + (parsedDate == null ? this.date : parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";
    }

    public String getDate() {
        return "|" + (parsedDate == null ? this.date : parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public LocalDate parseDate(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
