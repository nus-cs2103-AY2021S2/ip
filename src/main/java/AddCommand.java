/**
 * Represents a command that adds tasks.
 */
public class AddCommand implements Command {

    private final String commandType;
    private final String description;
    private Task newTask;
    private int numTasks;

    /**
     * Constructor for AddCommand class command name and description.
     * @param command Name of the command.
     * @param description Description of the command.
     */
    public AddCommand(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    /**
     * Returns positive if command terminates chat bot.
     * @return True if this command terminates chat bot.
     */
    public boolean shouldExit() {
        return false;
    }

    /**
     * 
     */
    public TaskList execute(TaskList taskList) {

        int taskID = taskList.getSize() + 1;
        switch (commandType) {
            case "todo":
                newTask = new ToDoTask(description, taskID);
                break;
            case "event":
                newTask = new EventTask(description, taskID);
                break;
            case "deadline":
                newTask = new DeadlineTask(description, taskID);
                break;
            default:
                newTask = new ToDoTask(description, taskID);
                break;
        }
        taskList.addTask(newTask);
        numTasks = taskList.getSize();
        return taskList;
    }

    public String getResponse() {
        return "Got it. I've added this task:\n  " + newTask.toString()
        + "\nNow you have " + numTasks + " tasks in the list.\n";
    }

}
