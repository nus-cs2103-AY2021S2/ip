import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDateInfo;
    private LocalDate endDateInfo;

    public Event(String taskInfo, LocalDate startDateInfo, LocalDate endDateInfo) {
        super(taskInfo);
        this.startDateInfo = startDateInfo;
        this.endDateInfo = endDateInfo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " ("
                + startDateInfo.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to "
                + endDateInfo.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
