package lihua.commands;

import lihua.commons.Messages;
import lihua.tasks.Task;

/**
 * Command class representing a command to delete a task.
 */
public class DeleteCommand extends Command {
    /** Command help information for deleting command */
    public static final String MESSAGE_USAGE = "delete: Remove a specific task from the list.\n"
            + "---- Example: delete [valid index number]";

    /**
     * Initializes a new DeleteCommand with a task index.
     *
     * @param targetIndex The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    /**
     * Executes the deleting command.
     * Delete the task from the list and give feedback to user.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        try {
            Task deleted = tasks.removeTask(targetIndex); // IndexOutOfBound Exception should be handled
            assert tasks.getSize() >= 0;
            String noun = tasks.getSize() <= 1 ? "task" : "tasks";
            String message = String.format("Got it. I have removed this task from your list:\n---- %s\n"
                            + "Now you have %d %s in total. Good luck.", deleted.toString(), tasks.getSize(), noun);
            return new CommandResult(message);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_INVALID_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        }
    }
}
