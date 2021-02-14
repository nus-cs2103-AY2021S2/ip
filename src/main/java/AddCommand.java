public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskList taskList, Ui ui) {
        return taskList.addTask(task);
    }
}
