public class ByeCommand extends Command {

    public ByeCommand(String details) throws DukeException {
        if (!details.isBlank()) {
            throw new DukeException("Wait, you want to leave or what?");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printFarewell();
        storage.saveTasksToFile(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
