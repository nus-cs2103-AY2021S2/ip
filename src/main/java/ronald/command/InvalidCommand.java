package ronald.command;

import ronald.RonaldException;

/**
 * Class containing data and methods specific to an Invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Throws DukeException.
     *
     * @throws RonaldException when an invalid command is read.
     */
    @Override
    public void process() throws RonaldException {
        throw new RonaldException("That doesn't seem to be an item on our menu...");
    }
}
