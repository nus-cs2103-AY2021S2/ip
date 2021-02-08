public class FindCommand extends Command {

    public FindCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui,
                        Storage storage, Statistics stat) throws DukeException {

        assert info != null;
        if (info.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        } else {
            ui.showFind(tasks.findTask(info));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
