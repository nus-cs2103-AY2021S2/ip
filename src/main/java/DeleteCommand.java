public class DeleteCommand extends Command {
    protected String fullCommand;

    public DeleteCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(this.fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
