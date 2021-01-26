import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, TaskType taskType, String at) throws DukeException {
        super(description, taskType);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeException e) {
            throw new DukeException("Date is not in the correct format");
        }
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
