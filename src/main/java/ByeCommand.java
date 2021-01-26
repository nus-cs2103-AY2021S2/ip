public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printExitMsg();
        storage.write(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
