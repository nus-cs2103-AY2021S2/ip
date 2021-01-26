import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public Deadline(boolean isDone, String description) {
        super("D", isDone, description, Parser.getDate(description), Parser.getTime(description));
    }

    public String modifiedDescription() {
        String formatDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatTime = time.format(DateTimeFormatter.ofPattern("hh.mm a"));
        String formatDateTime = "(by: " + formatDate + " " + formatTime + ")";
        return description.substring(0, description.indexOf('/')) + formatDateTime;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + modifiedDescription();
    }
}
