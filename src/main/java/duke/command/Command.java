package duke.command;

import duke.main.DukeException;

public class Command {
    protected Command() {

    }

    public String[] execute() throws DukeException {
        throw new DukeException("Parent command class does not have this method.");
    }
}
