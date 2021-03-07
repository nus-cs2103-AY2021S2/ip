public class ExitCommand extends Command {

    public ExitCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui,
                        Storage storage, Statistics stat) throws DukeException {
        ui.showGoodBye();
        storage.store(tasks.list);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
