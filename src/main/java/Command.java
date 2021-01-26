import java.io.IOException;

public abstract class Command {
    protected String[] parsedCommand;

    protected Command(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    abstract void execute(TaskManager taskManager,Ui ui, Storage storage) throws DukeException, IOException;

    public boolean isExit() {
        return false;
    }
}
