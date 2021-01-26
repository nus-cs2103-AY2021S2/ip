import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dateInfo;

    public Deadline(String taskInfo, LocalDate dateInfo) {
        super(taskInfo);
        this.dateInfo = dateInfo;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dateInfo.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
