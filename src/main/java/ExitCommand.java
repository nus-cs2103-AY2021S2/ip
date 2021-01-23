public class ExitCommand extends Command {

    public ExitCommand(String action, String info, String time) {
        super(action, info, time);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.store(tasks.list);
        ui.showGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
