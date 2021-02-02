public class DoneCommand extends Command {

    public DoneCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int num;
        Task task;
        String respone;

        try {
            num = Integer.parseInt(info);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! There is no such task number.");
        }

        if (num > tasks.size || num == 0) {
            throw new DukeException("OOPS!!! There is no such task number.");
        } else {
            num--;
            tasks.doneTask(num);
            task = tasks.list.get(num);
            respone = ui.showDone(task);
            storage.store(tasks.list);
        }

        return respone;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
