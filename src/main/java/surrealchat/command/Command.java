package surrealchat.command;

import surrealchat.exception.SurrealException;
import surrealchat.task.TaskManagement;

/**
 * Base class for user commands.
 */
public abstract class Command {
    protected final String commandType;

    /**
     * Creates new Command object.
     * @param commandType Type of command.
     */
    public Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Executes the user command to give a String output.
     * @param taskManagement TaskManagement object that handles Task objects relevant to command.
     * @return String to be printed.
     */
    public abstract String execute(TaskManagement taskManagement);

    /**
     * Checks if taskNumber falls outside the range [1, size of task list].
     * @param taskNumber Number keyed in by user.
     * @param size Total number of tasks in list.
     * @return True if out of range, false if within range.
     */
    protected static boolean isInvalidTaskNumber(int taskNumber, int size) {
        return (taskNumber <= 0 || taskNumber > size);
    }

    /**
     * Obtains int from String.
     * @param description String to be parsed to int.
     * @return Int inside the String.
     * @throws SurrealException If description cannot be parsed to String.
     */
    protected static int getInputNumber(String description) throws SurrealException {
        description = description.trim();
        if (description.isEmpty()) {
            throw new SurrealException(
                    "Did you forget to put a number for the command you just typed in? Not stonks!\n");
        } else {
            try {
                return Integer.valueOf(description);
            } catch (NumberFormatException e) {
                throw new SurrealException(
                        "Did you put something other than a number or did you put a number incorrectly? Not stonks!\n");
            }
        }
    }
}
