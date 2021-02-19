package command;

import task.TaskManager;
import util.DukeException;

/**
 * Command class representing a future action that can be executed on the supplied
 * TaskManager object.
 */
public abstract class Command {
    /**
     * Performs an action on the supplied taskManager and returns a String response
     * based on the action that was performed.
     *
     * @param taskManager TaskManager object to perform the action on.
     * @return String response of the action that was performed.
     * @throws DukeException Whenever the supplied action cannot be performed on
     *                       the given
     */
    public abstract String execute(TaskManager taskManager) throws DukeException;

    /**
     * Indicates if the Command object should signal to the application that the
     * user wants to terminate it.
     *
     * @return True, if the Command should tell the application to end.
     */
    public boolean isQuitCommand() {
        return false;
    }

    public abstract boolean equals(Object o);
}