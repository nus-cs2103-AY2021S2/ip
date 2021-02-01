import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadline(String taskDescription, String dateAsString, String timeAsString) {
        super(taskDescription);
        this.date = LocalDate.parse(dateAsString);
        this.time = LocalTime.parse(timeAsString);
    }

    public Deadline(String taskDescription, boolean isDone, String dateAsString, String timeAsString) {
        super(taskDescription, isDone);
        this.date = LocalDate.parse(dateAsString);
        this.time = LocalTime.parse(timeAsString);
    }

    @Override
    public String toString() {
        return "DEADLINE" + Task.TASK_DELIMITER + (isDone ? "done" : " ") + Task.TASK_DELIMITER + taskDescription + Task.TASK_DELIMITER + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ", " + time;
    }
}
