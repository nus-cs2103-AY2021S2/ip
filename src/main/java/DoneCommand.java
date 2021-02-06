public class DoneCommand extends Command {

    public DoneCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int num;
        Task task;

        try {
            num = Integer.parseInt(info);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! There is no such task number.");
        }

        assert tasks.size > 0;
        if (num > tasks.size || num <= 0) {
            throw new DukeException("OOPS!!! There is no such task number.");
        } else {
            num--;
            tasks.doneTask(num);
            task = tasks.list.get(num);
            ui.showDone(task);
            storage.store(tasks.list);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
