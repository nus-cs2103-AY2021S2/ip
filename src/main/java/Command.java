public abstract class Command {
    protected final String fullCommand;
    protected final String action;

    public Command(String fullCommand, String action) {
        this.fullCommand = fullCommand;
        this.action = action;
    }

    public abstract void execute(TaskList tasks) throws DukeException;
}
