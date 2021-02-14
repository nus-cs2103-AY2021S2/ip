package prerthan.duke.command;

import prerthan.duke.IO.Storage;
import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.task.TaskList;

/**
 * DoneCommand
 */
public class DoneCommand extends Command {
    private int position;

    DoneCommand(String[] commandTokens, int position) throws DukeInvalidArgumentException {
        super(commandTokens);
        this.commandName = CommandName.DONE;
        this.position = position;

        if (this.argumentTokens.length != 2 || !this.argumentTokens[1].matches("\\d+"))
            throw new DukeInvalidArgumentException(null, DeleteCommand.class.getSimpleName());
    }

    /**
     * 
     * @param commandTokens
     * @throws DukeInvalidArgumentException
     */
    public DoneCommand(String[] commandTokens) throws DukeInvalidArgumentException {
        this(commandTokens, Integer.parseInt(commandTokens[1]));
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (!(this.position >= 1) && this.position <= tasks.numberOfTasks()) {
            tasks.deleteTaskAtPosition(position);
        }
    }
}