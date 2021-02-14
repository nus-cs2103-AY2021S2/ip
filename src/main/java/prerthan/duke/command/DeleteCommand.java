package prerthan.duke.command;

import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.io.Output;
import prerthan.duke.io.Storage;
import prerthan.duke.task.TaskList;

/**
 * A user
 */
public class DeleteCommand extends Command {
    private int position;

    /**
     * Creates a Command that deletes a {@link TaskList}.
     *
     * @param argumentTokens The arguments provided to this {@link Command}
     * @throws DukeInvalidArgumentException If more than one argument is supplied, or the
     *                                      supplied argument is not strictly numerical and
     *                                      positive.
     */
    public DeleteCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);
        if (argumentTokens.length != 1 || !argumentTokens[0].matches("\\d+")) {
            throw new DukeInvalidArgumentException(
                "Delete command should have only one positive number as argument.", argumentTokens,
                commandName, this.getClass().getSimpleName());
        }
        this.position = Integer.parseInt(this.argumentTokens[0]);
    }

    @Override public void execute(TaskList tasks, Storage storage, Output output) {
        if (this.position >= 1 && this.position <= tasks.numberOfTasks()) {
            output.sayTaskDeleted(tasks.getTaskAtPosition(position));
            tasks.deleteTaskAtPosition(position);
        }
    }
}