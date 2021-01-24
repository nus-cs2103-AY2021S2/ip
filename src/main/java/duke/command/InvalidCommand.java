package duke.command;

import duke.DukeException;

public class InvalidCommand extends Command {
    @Override
    public void process() throws DukeException {
        throw new DukeException("That doesn't seem to be an item on our menu...");
    }
}
