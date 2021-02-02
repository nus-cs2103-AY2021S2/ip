package duke.command;

import duke.DukeException;
import duke.TaskList;
/**
 * An interface for commands that the user inputs.
 */
public interface Command {

    /**
     * Returns a boolean value to signal the bot to exit.
     * @return True if command signals bot to be terminated.
     */
    boolean shouldExit();

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    String getResponse();

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    TaskList execute(TaskList taskList) throws DukeException;
}
