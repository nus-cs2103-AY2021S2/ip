public class TodoCommand extends Command {

    public TodoCommand(String info) {
        super(info);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task;
        int size;
        Task t;

        task = Parser.getTask(info);

        if (task.equals("")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        }

        tasks.addTodo(task);
        size = tasks.size;
        t = (tasks.list).get(size - 1);

        ui.showAddMessage(t, size);
        storage.store(tasks.list);
    }

    public boolean isExit() {
        return false;
    }

}
