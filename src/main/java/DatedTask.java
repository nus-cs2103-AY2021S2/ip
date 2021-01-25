import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DatedTask extends Task {
    LocalDate date;

    public DatedTask(String task, String date) throws TaskException {
        super(task);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new TaskException("Incorrect date format. " + e.getMessage());
        }
    }

    private static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + formatDate(this.date) + ")";
    }
}
