public class ListCommand extends Command {

    private static final String ERROR_MSG = "Try typing 'list' only!";

    public ListCommand(String description) throws DukeException {
        if (!description.equals("")) {
            throw new DukeException(ERROR_MSG);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ui.printTaskList(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
