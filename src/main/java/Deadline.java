import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline (String description, LocalDateTime by)
            throws Task.EmptyDescriptionException  {
        super(description); this.by = by;
    }

    public Deadline (String description, boolean isDone, LocalDateTime createdDateTime,
            LocalDateTime by) throws Task.EmptyDescriptionException {
        super(description, isDone, createdDateTime);
        this.by = by;
    }

    @Override
    public String taskInformation (DateTimeFormatter outputFormat) {
        return "[D]" + super.taskInformation(outputFormat) + " [ by: " + 
                this.by.format(outputFormat) + " ]";
    }

    @Override
    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        return "D" + delimiter + super.toCommand(delimiter, parseFormat) + delimiter + 
                this.by.format(parseFormat);
    }
}
