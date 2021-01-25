import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime By;

    Deadline (String description, LocalDateTime by) {
        super(description); this.By = by;
    }

    Deadline (String[] parsedCommand) {
        // formar as TYPE | STATE | DESCRIPTION | DATETIME | BY
        super(Integer.parseInt(parsedCommand[1]), parsedCommand[2],
                LocalDateTime.parse(parsedCommand[3], Task.parseFormat));

        this.By = LocalDateTime.parse(parsedCommand[4], Task.parseFormat);
    }

    @Override
    public String taskInformation () {
        return "[D]" + super.taskInformation() + " [ by: " + 
                this.By.format(Task.outputFormat) + " ]";
    }

    @Override
    public String taskParseCommand () {
        return "D :: " + super.taskParseCommand() + " :: " + 
                this.By.format(Task.parseFormat);
    }
}
