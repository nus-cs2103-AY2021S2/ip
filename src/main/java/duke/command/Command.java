package duke.command;

import duke.Exceptions.DukeOutOfBoundsException;
import duke.Exceptions.DukeStorageException;
import duke.Model.TaskList;
import duke.Storage.Storage;
import duke.Ui.MessageGenerator;

public abstract class Command {
    protected boolean isExitAfterExecution = false;

    public abstract CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage)
            throws DukeStorageException, DukeOutOfBoundsException;


}
