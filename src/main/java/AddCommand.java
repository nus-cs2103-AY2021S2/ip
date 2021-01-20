public abstract class AddCommand implements Command{
    protected boolean isExitCommand = false;
    protected String taskDescription;
    protected TaskList taskList;

    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public boolean isExitCommand() {
        return isExitCommand;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        Task taskToAdd = new ToDo(this.taskDescription);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }

    @Override
    public String getResponse() {
        return "added: " + this.taskDescription;
    }
}
