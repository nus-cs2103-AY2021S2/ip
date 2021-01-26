package duke.command;

import duke.TaskList;
/**
 * Represents a command that lists all tasks on the task list.
 */
public class ListCommand implements Command {

    private TaskList currentList;

    /**
     * Returns a boolean value to signal the bot to exit.
     * @return True if command signals bot to be terminated.
     */
    public boolean shouldExit() {
        return false;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    public String getResponse() {
        return currentList.toString();
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public TaskList execute(TaskList taskList) {
        currentList = taskList;
        return taskList;
    }

}
