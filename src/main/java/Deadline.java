import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getSaveString() {
        String datetimeString = deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (this.isDone()) {
            return String.format("deadline [isDone] %s /by %s\n", description, datetimeString);
        } else {
            return String.format("deadline %s /by %s\n", description, datetimeString);
        }
    }

    @Override
    public String toString() {
        String datetimeString = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("[D][%s] %s (by: %s)", getStatus(), description, datetimeString);
    }
}