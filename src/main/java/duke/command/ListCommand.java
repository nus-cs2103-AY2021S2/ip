package duke.command;

import duke.TaskList;
/**
 * Represents a command that lists all tasks on the task list.
 */
public class ListCommand implements Command {

    private TaskList currentList;
    private boolean isAnomalyPresent;

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
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        sb.append(currentList.toString());
        sb.append("\n\n");
        if (isAnomalyPresent) {
            sb.append("NOTE: There are tasks in your list that have clashing deadlines, please check the list above.");
        }
        return sb.toString();
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public TaskList execute(TaskList taskList) {
        currentList = taskList;
        isAnomalyPresent = taskList.isAnomalyPresent();
        return taskList;
    }

}
