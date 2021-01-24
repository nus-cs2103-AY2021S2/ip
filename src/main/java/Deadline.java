import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    LocalDate dateTime;

    public Deadline(String taskName, LocalDate dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
