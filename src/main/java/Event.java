import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, TaskType taskType, String at) {
        super(description, taskType);
        this.at = LocalDate.parse(at);
    }
    
    @Override
    public String saveTaskString() {
        String delimiter = " ~ ";
        return super.saveTaskString() + delimiter + this.at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
