package prerthan.duke.command;

import prerthan.duke.IO.Storage;
import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.exception.DukeInvalidCommandException;
import prerthan.duke.task.TaskList;

/**
 * ListCommand
 */
public class ListCommand extends Command {
    /**
     * 
     * @param commandTokens
     * @throws DukeInvalidCommandException
     */
    public ListCommand(String[] commandTokens) throws DukeInvalidArgumentException {
        super(commandTokens);
        this.commandName = CommandName.LIST;

        if (commandTokens.length != 1)
            throw new DukeInvalidArgumentException(null, ListCommand.class.getSimpleName());
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.listTasks();
    }
}