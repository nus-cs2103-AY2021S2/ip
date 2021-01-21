public abstract class AddCommand implements Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList) {
        taskList.addTask(task);
        return String.format(DukeStrings.MESSAGE_ADDED, task.toString(), taskList.size());
    }
}
