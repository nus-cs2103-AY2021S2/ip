package duke.command;

import duke.DukeException;

/**
 * Abstract class which is inherited by all Command classes.
 */
public abstract class Command {
    protected String[] command;

    public abstract void process() throws DukeException;
}
