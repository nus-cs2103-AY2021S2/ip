package duke.command;

import duke.DukeStorageException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    protected boolean isExitAfterExecution = false;

    public abstract CommandResult execute(Ui ui, TaskList tasks, Storage storage) throws DukeStorageException;


}
