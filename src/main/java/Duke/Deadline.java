package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    private String description;

    public Deadline(LocalDateTime date, String description) {
        super(description + " " + date);
        this.date = date;
        this.description = description;
    }

    @Override
    public String formatToSave() {
        String str = "D | ";
        if (done) {
            str += "X |";
        } else {
            str += "O |";
        }
        str += " " + description + " | by: " + date.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
        return str;
    }

    @Override
    public String toString() {
        String str = "[D]";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + description + " (by: " + date.format(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm")) + ")";
        return str;
    }
}