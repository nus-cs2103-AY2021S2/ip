import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToDo extends Task {
    public ToDo (String description) throws Task.EmptyDescriptionException {
        super(description);
    }

    public ToDo (String description, boolean isDone, LocalDateTime createdDateTime)
            throws Task.EmptyDescriptionException {
        super(description, isDone, createdDateTime);
    }

    @Override
    public String taskInformation (DateTimeFormatter outputFormat) {
        return "[T]" + super.taskInformation(outputFormat);
    }

    @Override
    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        return "T" + delimiter + super.toCommand(delimiter, parseFormat);
    }
}