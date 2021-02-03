public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String excute(TaskList taskList) {
        return taskList.addTask(task);
    }
}
