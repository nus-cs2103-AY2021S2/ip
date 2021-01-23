public class AddCommand extends Command {

    public AddCommand(String action, String info, String time) {
        super(action, info, time);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (info.equals("")) {
            throw new DukeException("☹ OOPS!!! The description cannot be empty.");
        }

        tasks.addTask(action, info, time);
    }

    public boolean isExit() {
        return false;
    }
}
