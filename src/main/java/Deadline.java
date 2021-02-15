import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java. text. DateFormat;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public LocalDate getBy() {
        return this.date;
    }

    public String getDateString() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
    }
}