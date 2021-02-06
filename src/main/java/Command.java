import java.util.ArrayList;

public abstract class Command {
    protected String input;
    protected String[] parts;
    protected TaskList tasks;

    Command (String input, String[] parts, TaskList tasks) {
        this.input = input;
        this.parts = parts;
        this.tasks = tasks;
    }

    public abstract String execute() throws InsufficientArgumentsException;
}
