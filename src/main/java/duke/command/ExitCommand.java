package duke.command;

import duke.exception.DukeCommandException;

public class ExitCommand extends Command {
    /**
     * Execute the exit command to tell the Duke to exit
     * @throws DukeCommandException
     */
    @Override
    public void execute() throws DukeCommandException {
    }

    /**
     * Returns true to indicate that the chat bot will be exiting
     * @return True boolean to indicate that the bot is exiting
     */
    @Override
    public boolean isToExit() {
        return true;
    }
}
