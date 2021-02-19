package duke.logic.commands;

import duke.commons.exceptions.DukeException;
import duke.model.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * An abstract base class of executable commands.
 */
public abstract class Command {
    /**
     * Performs the execution of the desired command.
     */
    public abstract CommandResponse execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
