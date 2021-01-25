public class DoneCommand extends Command {

    public DoneCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        int num;
        Task t;

        try {
            num = Integer.parseInt(info);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! There is no such task number.");
        }

        if (num > tasks.size || num == 0) {
            throw new DukeException("☹ OOPS!!! There is no such task number.");
        } else {
            num--;
            tasks.doneTask(num);
            t = tasks.list.get(num);
            ui.showDone(t);
            storage.store(tasks.list);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
