import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String separator = " | ";
    protected LocalDateTime dateTime;
    private String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    /**
     * Construct a Task with description and its completeness status.
     * @param description description of the task to be constructed.
     * @param isDone true if the task is marked as done; otherwise, false.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.dateTime = null;
        this.isDone = isDone;
    }

    /**
     * Construct a Task with description, time and its completeness status.
     * @param description description of the task to be constructed.
     * @param time time of the task in "yyyy-mm-dd hh:mm" format.
     * @param isDone true if the task is marked as done; otherwise, false.
     */
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, format);
        return dateTime;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + separator + this.getDescription();
    }
}
