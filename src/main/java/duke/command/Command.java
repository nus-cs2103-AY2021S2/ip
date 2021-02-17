package duke.command;

import duke.exceptions.DukeOutOfBoundsException;
import duke.exceptions.DukeStorageException;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.MessageGenerator;

public abstract class Command {
    protected boolean isExitAfterExecution = false;

    public abstract CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage)
            throws DukeStorageException, DukeOutOfBoundsException;


}
