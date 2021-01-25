package lihua.commands;

import lihua.commons.Messages;
import lihua.tasks.Task;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Get a specific task done.\n"
            + "---- Example: " + COMMAND_WORD + " [valid index number]";
    public DoneCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            Task done = tasks.getTaskDone(targetIndex); // IndexOutOfBound Exception should be handled
            String message = String.format(
                    "Got it. I have mark this task as done:\n%s\n", done.toString());
            return new CommandResult(message);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_INVALID_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        }
    }
}
