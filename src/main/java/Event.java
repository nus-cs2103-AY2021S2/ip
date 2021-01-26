import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;

    public Event (String description, LocalDateTime at) 
        throws Task.EmptyDescriptionException {

        super(description); this.at = at;
    }

    public Event (String description, boolean isDone, LocalDateTime createdDateTime,
            LocalDateTime at) throws Task.EmptyDescriptionException {

        super(description, isDone, createdDateTime);
        this.at = at;
    }

    @Override
    public String taskInformation (DateTimeFormatter outputFormat) {
        return "[E]" + super.taskInformation(outputFormat) + " [ at: " + 
                this.at.format(outputFormat) + " ]";
    }

    @Override
    public String toCommand (String delimiter, DateTimeFormatter parseFormat) {
        return "E" + delimiter + super.toCommand(delimiter, parseFormat) + delimiter + 
                this.at.format(parseFormat);
    }
}