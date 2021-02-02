public class DeleteCommand extends Command {

    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        storage.writeToFile(taskList.getList());
        ui.showTaskDeleted(task);
    }
}
