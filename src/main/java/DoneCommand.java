public class DoneCommand extends Command {

    public DoneCommand(String action, String info, String time) {
        super(action, info, time);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        int num = 0;

        try {
            num = Integer.parseInt(info);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! There is no such task number.");
        }

        if (num > tasks.size || num == 0) {
            throw new DukeException("☹ OOPS!!! There is no such task number.");
        } else {
            tasks.doneTask(num - 1);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
