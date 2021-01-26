package customClass;

import customClass.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String saveString() {
        return isDone ? "E --- 1 --- " + description + " --- " + at : "E --- 0 --- " + description + " --- " + at;
    }

    public static String convertToDate(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeException e) {
            return input;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Event.convertToDate(at) + ")";
    }
}
