public abstract class Command {
    protected String arguments;
    protected boolean isExit;

    public Command(String arguments) {
        this.arguments = arguments;
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
