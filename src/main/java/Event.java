import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that can create a event task object.
 */
public class Event extends Task {
    private static final String EVENT_DISPLAY_ICON = "[E]";
    public static final String EVENT_DATA_ICON = "E";
    private final LocalDate startDateInfo;
    private final LocalDate endDateInfo;

    /**
     * Constructor that creates a event task.
     * @param taskInfo the description of the event task.
     * @param startDateInfo the start date of the event task.
     * @param endDateInfo the end date of the event task.
     */
    public Event(String taskInfo, LocalDate startDateInfo, LocalDate endDateInfo) {
        super(taskInfo);
        this.startDateInfo = startDateInfo;
        this.endDateInfo = endDateInfo;
    }

    @Override
    public String toString() {
        return EVENT_DISPLAY_ICON + super.toString() + " ("
                + startDateInfo.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to "
                + endDateInfo.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    public String getData() {
        if (isDone) {
            return EVENT_DATA_ICON + DELIMITER + IS_DONE_TRUE_DATA_ICON + DELIMITER + taskInfo
                    + DELIMITER + startDateInfo + DELIMITER + endDateInfo;
        } else {
            return EVENT_DATA_ICON + DELIMITER + IS_DONE_FALSE_DATA_ICON + DELIMITER + taskInfo
                    + DELIMITER + startDateInfo + DELIMITER + endDateInfo;
        }
    }
}
