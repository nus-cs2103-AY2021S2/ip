public class AddCommand extends Command {

    public AddCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task, time;
        int size;
        Task t;

        if (action.equals("todo")) {
            task = Parser.getTask(info);
            time = "";
        } else if (action.equals("deadline")) {
            task = Parser.getTask(info);
            time = Parser.getTimeBy(info);
        } else {
            task = Parser.getTask(info);
            time = Parser.getTimeAt(info);
        }

        tasks.addTask(action, task, time);
        size = tasks.size;
        t = (tasks.list).get(size - 1);
        ui.showAdd(t, size);
        storage.store(tasks.list);
    }

    public boolean isExit() {
        return false;
    }

}
