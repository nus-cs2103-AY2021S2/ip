package lihua.commands;

import lihua.commons.Messages;
import lihua.tasks.Task;

/**
 * Command class representing a command to get a task done.
 */
public class DoneCommand extends Command {
    /** Command help information for done command */
    public static final String MESSAGE_USAGE = "done: Get a specific task done.\n"
            + "---- Example: done [valid index number]";

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
            assert tasks.getSize() >= 0;
            String message = String.format("Got it. I have mark this task as done:\n---- %s", done.toString());
            return new CommandResult(message);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_INVALID_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        }
    }
}
