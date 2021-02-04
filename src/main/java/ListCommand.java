public class ListCommand extends Command {

    public ListCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response;
        if (tasks.size == 0) {
            throw new DukeException("OOPS!!! There is currently no tasks for you.");
        } else {
            response = ui.showList() + "\n" + tasks.listTask();
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
