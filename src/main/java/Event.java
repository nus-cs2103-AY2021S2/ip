import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime At;

    Event (String Description, LocalDateTime at) {
        super(Description); this.At = at;
    }

    Event (String[] parsedCommand) {
        // formar as TYPE | STATE | DESCRIPTION | DATETIME | AT
        super(Integer.parseInt(parsedCommand[1]), parsedCommand[2],
                LocalDateTime.parse(parsedCommand[3], Task.parseFormat));

        this.At = LocalDateTime.parse(parsedCommand[4], Task.parseFormat);
    }

    @Override
    public String taskInformation () {
        return "[E]" + super.taskInformation() + " [ at: " + 
                this.At.format(Task.outputFormat) + " ]";
    }

    @Override
    public String taskParseCommand () {
        return "E :: " + super.taskParseCommand() + " :: " + 
                this.At.format(Task.parseFormat);
    }
}