package prerthan.duke.command;

import prerthan.duke.IO.Storage;
import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.exception.DukeInvalidCommandException;
import prerthan.duke.task.TaskList;

/**
 * A user
 */
public class DeleteCommand extends Command {
    private int position;

    DeleteCommand(String[] argumentTokens, int position) throws DukeInvalidArgumentException {
        super(argumentTokens);
        this.commandName = CommandName.DELETE;
        this.position = position;

        if (this.argumentTokens.length != 1 || !this.argumentTokens[1].matches("\\d+")
                || Integer.parseInt(this.argumentTokens[1]) <= 1)
            throw new DukeInvalidArgumentException("Delete command must be of the format: 'delete 12'",
                    DeleteCommand.class.getSimpleName());
    }

    /**
     * 
     * @param commandTokens
     * @throws NumberFormatException
     * @throws DukeInvalidCommandException
     */
    public DeleteCommand(String[] commandTokens) throws DukeInvalidArgumentException {
        this(commandTokens, Integer.parseInt(commandTokens[1]));
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (!(this.position >= 1) && this.position <= tasks.numberOfTasks()) {
            tasks.deleteTaskAtPosition(position);
        }
    }
}