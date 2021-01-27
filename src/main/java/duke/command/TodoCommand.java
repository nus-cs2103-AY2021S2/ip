package duke.command;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        super("todo");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
