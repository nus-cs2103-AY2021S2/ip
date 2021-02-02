public class ToDoCommand extends Command {
    private String todo;

    ToDoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(this.todo);
        tasks.add(task);
        ui.addtask(task.toString(), tasks.size());
        storage.savetasks(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
