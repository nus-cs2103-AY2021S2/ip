package duke.command;

import duke.DukeException;

public abstract class Command {
    protected String[] command;
    abstract public void process() throws DukeException;
}
