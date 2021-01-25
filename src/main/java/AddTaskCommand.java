public class AddTaskCommand extends Command {
    protected String type;
    protected String fullCommand;

    public AddTaskCommand(String type, String fullCommand) {
        super();
        this.type = type;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(this.type, this.fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
