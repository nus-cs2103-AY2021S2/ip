package duke.commands;

import static duke.utils.Messages.MESSAGE_DONE_TASK;
import static duke.utils.Messages.MESSAGE_INVALID_INDEX;

/**
 * Completes a task.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Mark a task as completed\n"
            + "Usage: done <task_number>"
            + "Example: done 2";

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            taskList.completeTask(index);
            return new CommandResult(MESSAGE_DONE_TASK + "\n"
                    + taskList.getTask(index).toString(), taskList);
        } catch (IndexOutOfBoundsException ex) {
            return new CommandResult(MESSAGE_INVALID_INDEX);
        }
    }
}
