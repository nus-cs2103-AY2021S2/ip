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
        this.taskToAdd = new TodoTask(this.taskDescription);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }

    @Override
    public String getResponse() {
        return String.format(" Got it. I've added this task: \n" +
                "       %s\n" +
                " Now you have %d tasks in the list.", this.taskToAdd.taskDescription, this.taskList.getNumTasks());
    }
}
