package duke.command;

import duke.task.TaskList;

/**
 * Represents the execution of a Command. All Commands should inherit this base class.
 */
public abstract class Command {
    /**
     * Returns true if processed command wants the bot to exit
     *
     * @return true if processed command wants the bot to exit
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the response of the bot after execution of command
     *
     * @param taskLst TaskList
     * @return response
     */
    public abstract String execute(TaskList taskLst);
}
