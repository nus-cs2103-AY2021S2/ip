package antonio.command;

import antonio.TaskList;
import antonio.task.Task;
/**
 * Represents a command that adds tasks.
 */
public abstract class AddCommand implements Command {

    protected String commandType;
    protected String description;
    protected Task newTask;
    protected int numTasks;

    /**
     * Constructs an AddCommand class command name and description.
     * @param commandType Name of the command.
     * @param description Description to add to command.
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
     * Gets the reply message.
     * @return The reply message for this command.
     */
    public String getResponse() {
        return "Tutto chiaro! I've added this task:\n  ";
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public abstract TaskList execute(TaskList taskList);
}
