public class AddCommand extends Command {

    public AddCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task;
        String time;
        String response;
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

        response = ui.showAddMessage(t, size);
        storage.store(tasks.list);
        return response;
    }

    public boolean isExit() {
        return false;
    }

}
