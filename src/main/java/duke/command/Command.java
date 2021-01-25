package duke.command;

import duke.TaskList;
/**
 * An interface for commands that the user inputs.
 */
public interface Command {

    /**
     * Returns a boolean value to signal the bot to exit.
     * @return True if duke.command signals bot to be terminated.
     */
    boolean shouldExit();

    /**
     * Gets the reply message.
     * @return The reply message from the bot.
     */
    String getResponse();

    /**
     * Executes the duke.command.
     * @param taskList List of tasks to be used for execution of the duke.command.
     * @return List of tasks after the execution of the duke.command.
     */
    TaskList execute(TaskList taskList);
}
