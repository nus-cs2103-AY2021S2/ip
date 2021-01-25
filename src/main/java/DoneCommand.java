public class DoneCommand extends Command {
    protected String fullCommand;

    public DoneCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.done(this.fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}