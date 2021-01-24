public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n  ";
    private static final String TASKLIST_SIZE_MESSAGE_FORMAT = "Now you have %d tasks in your list.";
    
    private String taskName;
    
    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }
    
    @Override
    public CommandResult execute() {
        ToDoTask task = new ToDoTask(taskName);
        taskList.addTask(task);
        return new CommandResult(ADDED_TASK_MESSAGE + task.toString() + "\n" +
                String.format(TASKLIST_SIZE_MESSAGE_FORMAT, taskList.size()), taskList);
    }
}
