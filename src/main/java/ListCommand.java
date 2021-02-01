public class ListCommand extends Command {

    public ListCommand(String action, String info) {
        super(action, info);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String respone;
        if (tasks.size == 0) {
            throw new DukeException("OOPS!!! There is currently no tasks for you.");
        } else {
            respone = ui.showList() + "\n" + tasks.listStrTask();
        }
        return respone;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
