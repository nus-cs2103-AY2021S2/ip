package lihua.commands;

import lihua.commons.Messages;
import lihua.tasks.Task;

/**
 * Command class representing a command to get a task done.
 */
public class DoneCommand extends Command {
    /** Command word for done command */
    public static final String COMMAND_WORD = "done";
    /** Command help information for done command */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Get a specific task done.\n"
            + "---- Example: " + COMMAND_WORD + " [valid index number]";

    /**
     * Initializes a new DoneCommand with a task index.
     *
     * @param targetIndex The 1-based index of the task to be done.
     */
    public DoneCommand(int targetIndex) {
        super(targetIndex);
    }

    /**
     * Executes the done command.
     * Get the task done and give feedback to user.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        try {
            Task done = tasks.getTaskDone(targetIndex); // IndexOutOfBound Exception should be handled
            String message = String.format(
                    "Got it. I have mark this task as done:\n---- %s", done.toString());
            return new CommandResult(message);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_INVALID_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        }
    }
}
