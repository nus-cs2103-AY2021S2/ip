public class EventCommand extends AddCommand {

    private String timeWindow;

    public EventCommand(String taskDescription, String timeWindow) {
        super(taskDescription);
        this.timeWindow = timeWindow;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        this.taskToAdd = new Event(this.taskDescription, this.timeWindow);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }
}
