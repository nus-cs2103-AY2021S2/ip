package lihua.commands;

import lihua.tasks.Task;
import lihua.commons.Messages;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Remove a specific task from the list.\n"
            + "---- Example: " + COMMAND_WORD + " [valid index number]";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            Task deleted = tasks.removeTask(targetIndex); // IndexOutOfBound Exception should be handled
            String noun = tasks.getSize() <= 1? "task": "tasks";
            String message = String.format(
                    "Got it. I have removed this task from your list:\n---- %s\n" +
                            "Now you have %d %s in total. Good luck.", deleted.toString(), tasks.getSize(), noun);
            return new CommandResult(message);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_INVALID_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        }
    }
}
