public class TodoCommand extends Command {

    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        taskList.addTodoTask(todo);
        storage.writeToFile(taskList.getList());
        ui.showTaskAdded(todo);
    }
}
