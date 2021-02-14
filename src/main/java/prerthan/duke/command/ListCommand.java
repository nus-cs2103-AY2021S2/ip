package prerthan.duke.command;

import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.exception.DukeInvalidCommandException;
import prerthan.duke.io.Output;
import prerthan.duke.io.Storage;
import prerthan.duke.task.TaskList;

/**
 * ListCommand
 */
public class ListCommand extends Command {
    /**
     * @param commandTokens
     * @throws DukeInvalidCommandException
     */
    public ListCommand(String[] commandTokens) throws DukeInvalidArgumentException {
        super(commandTokens);
        this.commandName = CommandName.LIST;

        if (commandTokens.length != 0) {
            throw new DukeInvalidArgumentException("List command must have no arguments.",
                                                   commandTokens, this.commandName,
                                                   this.getClass().getSimpleName());
        }

    }

    @Override public void execute(TaskList tasks, Storage storage, Output output) {
        tasks.listTasks(output);
    }
}