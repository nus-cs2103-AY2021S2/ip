public class DoneCommand extends Command {

    public DoneCommand(int index) {
        super(index);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.taskDone(index);
        storage.writeToFile(taskList.getList());
        ui.showTaskDone(task);
    }
}
