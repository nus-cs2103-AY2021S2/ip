import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private boolean isDone;
    protected String separator = " | ";
    protected LocalDateTime dateTime;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.dateTime = null;
        this.isDone = isDone;
    }

    public Task(String description, String time, boolean isDone) {
        this.description = description;
        this.dateTime = formatToDateTime(time);
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isSameDay(LocalDate targetDate) {
            return this.dateTime.toLocalDate().isEqual(targetDate);
    }

    private LocalDateTime formatToDateTime(String time) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
        return dateTime;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + separator + this.getDescription();
    }
}