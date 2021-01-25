public class FindCommand extends Command {

    public FindCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (info.equals("")) {
            throw new DukeException("☹ OOPS!!! The description cannot be empty.");
        } else {
            ui.showFind();
            tasks.findTask(info);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
