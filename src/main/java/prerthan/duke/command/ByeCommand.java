package prerthan.duke.command;

import prerthan.duke.IO.Storage;
import prerthan.duke.exception.DukeEmptyDetailException;
import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.exception.DukeInvalidDateException;
import prerthan.duke.task.TaskList;

/**
 * ByeCommand
 */
public class ByeCommand extends Command {

    public ByeCommand(String argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);
        this.commandName = CommandName.BYE;

        if (argumentTokens.length() >= 1) {
            throw new DukeInvalidArgumentException("Bye command must have no arguments.",
                    ByeCommand.class.getSimpleName());
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeEmptyDetailException, DukeInvalidDateException {

    }
}