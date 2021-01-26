public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ui.printExitMsg();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
