public class AddCommand implements Command{
    private boolean isExitCommand = false;
    private String taskDescription;
    private TaskList taskList;

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
