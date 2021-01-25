public class ListCommand extends Command {

    public ListCommand(String action, String info) {
        super(action, info);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size == 0) {
            throw new DukeException("☹ OOPS!!! There is currently no tasks for you.");
        } else {
            ui.showList();
            tasks.listTask();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
