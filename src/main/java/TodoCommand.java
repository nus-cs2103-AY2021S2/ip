public class TodoCommand extends AddCommand {

    public TodoCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean isExitCommand() {
        return super.isExitCommand;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        Task taskToAdd = new ToDo(super.taskDescription);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }

    @Override
    public String getResponse() {
        return "added: " + this.taskDescription;
    }
}
