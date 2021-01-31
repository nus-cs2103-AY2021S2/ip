abstract class Command {
    private String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
