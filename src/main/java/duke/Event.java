package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;

    public Event(LocalDateTime startDate, LocalDateTime endDate, String description) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @Override
    public String formatToSave() {
        String str = "E | ";
        if (isDone) {
            str += "X |";
        } else {
            str += "O |";
        }
        str += " " + description + " | from: " + startDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"))
                + " | to: " + endDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
        return str;
    }

    @Override
    public String toString() {
        String str = "[E]";
        if (isDone) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + description + " (from: " + startDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"))
                + " - " + endDate.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm")) + ")";
        return str;
    }
}