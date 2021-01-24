public class ListTaskCommand extends Command {

    public ListTaskCommand(String details) throws DukeException {
        if (!details.isBlank()) {
            throw new DukeException("Wait, you want me to list all the tasks or what?");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printAllTasks(tasks);
        storage.saveTasksToFile(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
