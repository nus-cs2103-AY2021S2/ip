public class DeadlineCommand extends AddCommand {

    private String deadline;

    public DeadlineCommand(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        this.taskToAdd = new DeadlineTask(this.taskDescription, this.deadline);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }
}
