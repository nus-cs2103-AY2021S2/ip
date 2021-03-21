package antonio.command;

import antonio.TaskList;

/**
 * Represents a command that exits the chat bot.
 */
public class ByeCommand implements Command {

    /**
     * Returns a boolean value to signal the bot to exit.
     * @return True if command signals bot to be terminated.
     */
    public boolean shouldExit() {
        return true;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    public String getResponse() {
        return "Arrivederci,\nHope to see you again soon.\nCiao!";
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
