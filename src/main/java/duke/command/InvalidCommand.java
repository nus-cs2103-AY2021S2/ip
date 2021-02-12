package duke.command;

import duke.DukeException;

/**
 * Class containing data and methods specific to an Invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Throws DukeException.
     *
     * @throws DukeException when an invalid command is read.
     */
    @Override
    public void process() throws DukeException {
        throw new DukeException("That doesn't seem to be an item on our menu...");
    }
}
