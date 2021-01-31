package duke.command;

import duke.exception.DukeCommandException;

/** An executable command to close the chat bot */
public class ExitCommand extends Command {
    /**
     * Executes the exit command to tell the Duke to exit
     *
     * @throws DukeCommandException
     */
    @Override
    public String execute() throws DukeCommandException {
        return "";
    }

    /**
     * Returns true to indicate that the chat bot will be exiting
     *
     * @return True boolean to indicate that the bot is exiting
     */
    @Override
    public boolean willExit() {
        return true;
    }
}
