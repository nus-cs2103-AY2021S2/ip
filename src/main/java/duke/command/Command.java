package duke.command;

import duke.*;

import java.io.IOException;

public abstract class Command {
    protected boolean isExitAfterExecution = false;

    public abstract CommandResult execute(Ui ui, TaskList tasks, Storage storage)
            throws DukeStorageException, DukeOutOfBoundsException;


}
