public class FindCommand extends Command {

    public FindCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String respone;
        if (info.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        } else {
            respone = ui.showFind() + "\n" + tasks.findTask(info);
        }
        return respone;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
