public class FindCommand extends Command {

    public FindCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response;
        if (info.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        } else {
            response = ui.showFind() + "\n" + tasks.findTask(info);
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
