package duke.command;

public class TodoCommand extends Command{
    String description;
    public TodoCommand(String description) {
        super("todo");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void run() {

    }
}
