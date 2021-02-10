import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that can create a deadline task object.
 */
public class Deadline extends Task {
    private static final String DEADLINE_DISPLAY_ICON = "[D]";
    public static final String DEADLINE_DATA_ICON = "D";
    private final LocalDate dateInfo;

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
        return DEADLINE_DISPLAY_ICON + super.toString() + " (" + dateInfo.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    public String getData() {
        if (isDone) {
            return DEADLINE_DATA_ICON + DELIMITER + IS_DONE_TRUE_DATA_ICON + DELIMITER + taskInfo
                    + DELIMITER + dateInfo;
        } else {
            return DEADLINE_DATA_ICON + DELIMITER + IS_DONE_FALSE_DATA_ICON + DELIMITER + taskInfo
                    + DELIMITER + dateInfo;
        }
    }
}
