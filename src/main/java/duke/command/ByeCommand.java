package duke.command;

import duke.TaskList;

/**
 * Represents a command that exits the chat bot.
 */
public class ByeCommand implements Command {

    public boolean shouldExit() {
        return true;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    public String getResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public TaskList execute(TaskList taskList) {
        return taskList;
    }

}
