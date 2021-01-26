public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void excute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(ui, this.task);
    }
}
