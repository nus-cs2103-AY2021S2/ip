import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String datetimeString = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("[D][%s] %s (by: %s)", getStatus(), description, datetimeString);
    }
}