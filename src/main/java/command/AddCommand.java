package command;
import mike.TaskList;
import task.Task;
import task.TodoTask;

public abstract class AddCommand implements Command {
    protected String taskDescription;
    protected TaskList taskList;
    protected Task taskToAdd;


    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Checks if chatbot should exit after command
     *
     * @return value of isExitCommand in Command.Command objects
     */
    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Add task to task list provided
     * @param taskList TaskList object to add the task to
     * @return TaskList object after adding task to the list
     */
    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        this.taskToAdd = new TodoTask(this.taskDescription);
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }

    /**
     * Get response to be encapsulated in the output handler
     * @return Formatted string response
     */
    @Override
    public String getResponse() {
        return String.format(
                "Got it. I've added this task: \n"
                        + "       %s\n"
                        + "Now you have %d tasks in the list.",
                this.taskToAdd.toString(), this.taskList.getNumTasks());
    }
}
