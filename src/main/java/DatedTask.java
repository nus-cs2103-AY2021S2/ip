import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DatedTask extends Task {
    LocalDate date;

    public DatedTask(String task, String date) throws TaskException {
        super(task);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy]");
            this.date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new TaskException("Incorrect date format. " + e.getMessage());
        }
    }

    protected static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
