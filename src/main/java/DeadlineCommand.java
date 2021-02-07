public class DeadlineCommand extends Command {

    public DeadlineCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui,
                        Storage storage, Statistics stat) throws DukeException {
        String task;
        String time;
        int size;
        Task t;

        task = Parser.getTask(info);
        if (task.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        }

        time = Parser.getTimeBy(info);
        time = Parser.parseTime(time);

        tasks.addDeadline(task, time);
        stat.changeStat(1, "deadline");
        size = tasks.size;
        t = (tasks.list).get(size - 1);

        ui.showAddMessage(t, size);
        storage.store(tasks.list);
    }

    public boolean isExit() {
        return false;
    }

}
