import java.time.LocalDateTime;

public class ToDo extends Task {
    ToDo (String description) {
        super(description);
    }

    ToDo (String[] parsedCommand) {
        // formar as TYPE | STATE | DESCRIPTION | DATETIME
        super(Integer.parseInt(parsedCommand[1]), parsedCommand[2],
                LocalDateTime.parse(parsedCommand[3], Task.parseFormat));
    }

    @Override
    public String taskInformation () {
        return "[T]" + super.taskInformation();
    }

    @Override
    public String taskParseCommand () {
        return "T :: " + super.taskParseCommand();
    }
}