public abstract class AddCommand implements Command{
    protected boolean isExitCommand = false;
    protected String taskDescription;
    protected TaskList taskList;
    protected Task taskToAdd;

    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public boolean isExitCommand() {
        return isExitCommand;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        this.taskToAdd = new Todo(this.taskDescription);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }

    @Override
    public String getResponse() {
        return "added: " + this.taskDescription;
    }
}
