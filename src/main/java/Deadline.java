import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.Date;

public class Deadline extends Task {

    private LocalDate deadline;
    private LocalTime deadlineTime;

    public Deadline(String details, LocalDate deadline, LocalTime deadlineTime) {
        super(details);
        this.deadline = deadline;
        this.deadlineTime = deadlineTime;
    }

    private Deadline(String details, LocalDate deadline, LocalTime deadlineTime, boolean indicator) {
        super(details, indicator);
        this.deadline = deadline;
        this.deadlineTime = deadlineTime;
    }

    // overrides completeTask() method
    public Deadline completeTask() {
        return new Deadline(this.getTask_details(), deadline, deadlineTime, true);
    }

    // overrides taskStatus() method
    public String taskStatus() {
        if (this.isDone()) {
            return "[D][X] "
                    + this.getTask_details()
                    + " (by: " + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " "
                    + deadlineTime.format(DateTimeFormatter.ofPattern("HHmm")) + ")";
        } else {
            return "[D][ ] "
                    + this.getTask_details()
                    + " (by: " + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " "
                    + deadlineTime.format(DateTimeFormatter.ofPattern("HHmm")) + ")";
        }
    }
}
