import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;
    private String description;

    public Event(LocalDateTime date, String description) {
        super(description);
        this.date = date;
        this.description = description;
    }

    @Override
    public String formatToSave() {
        String str = "E | ";
        if (done) {
            str += "X |";
        } else {
            str += "O |";
        }
        str += " " + description + " | at: " + date.format(DateTimeFormatter.ofPattern("dd-M-yyyy Hmm"));
        return str;
    }

    @Override
    public String toString() {
        String str = "[E]";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += " " + description + " (at: " + date.format(DateTimeFormatter.ofPattern("dd-M-yyyy Hmm")) + ")";
        return str;
    }
}