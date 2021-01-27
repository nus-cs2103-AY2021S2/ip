import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that can create a deadline task object.
 */
public class Deadline extends Task {
    private LocalDate dateInfo;

    /**
     * Constructor that creates a deadline task.
     * @param taskInfo the description of the deadline task.
     * @param dateInfo the date of the deadline for the task.
     */
    public Deadline(String taskInfo, LocalDate dateInfo) {
        super(taskInfo);
        this.dateInfo = dateInfo;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dateInfo.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    public String getData() {
        if (isDone == true) {
            return "D!@#1!@#" + taskInfo + "!@#" + dateInfo;
        } else {
            return "D!@#0!@#" + taskInfo + "!@#" + dateInfo;
        }
    }
}
