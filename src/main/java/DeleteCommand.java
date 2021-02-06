public class DeleteCommand extends Command {

    public DeleteCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int num;
        int size;
        Task t;

        assert info != null;
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
            t = (tasks.list).get(num);
            tasks.deleteTask(num);
            size = tasks.size;
            ui.showDelete(t, size);
            storage.store(tasks.list);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
