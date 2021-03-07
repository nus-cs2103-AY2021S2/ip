public class DeleteCommand extends Command {

    public DeleteCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui,
                        Storage storage, Statistics stat) throws DukeException {
        int num;

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
            int size;
            Task task;
            char action;
            char done;

            num--;
            task = (tasks.list).get(num);
            tasks.deleteTask(num);
            action = (task.toString()).charAt(1);
            done = (task.toString()).charAt(4);

            switch (action) {
            case 'T':
                stat.changeStat(-1, "todo");
                break;
            case 'D':
                stat.changeStat(-1, "deadline");
                break;
            case 'E':
                stat.changeStat(-1, "event");
                break;
            default:
                throw new DukeException("OOPS!!! There is an error in deleting the task.");
            }

            if (done == 'X') {
                stat.changeStat(-1, "done");
            }

            stat.changeStat(1, "delete");
            size = tasks.size;
            ui.showDelete(task, size);
            storage.store(tasks.list);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
