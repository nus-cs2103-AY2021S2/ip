public class DoneCommand extends Command {

    public DoneCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui,
                        Storage storage, Statistics stat) throws DukeException {
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

            task = tasks.list.get(num);
            if (task.getStatusIcon().equals("X")) {
                stat.changeStat(-1, "done");
            }

            tasks.doneTask(num);
            stat.changeStat(1, "done");
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
