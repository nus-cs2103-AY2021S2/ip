public class TodoCommand extends Command {

    public TodoCommand(String action, String info) {
        super(action, info);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task;
        String response;
        int size;
        Task t;

        task = Parser.getTask(info);

        tasks.addTodo(action, task);
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
