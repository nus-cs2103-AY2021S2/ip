package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * An abstract base class of executable commands.
 */
public abstract class Command {

    /** Indicator used to differentiate exit command */
    private final boolean shouldExit;

    public Command() {
        this.shouldExit = false;
    }

    public Command(boolean isExit) {
        this.shouldExit = isExit;
    }

    public boolean isExit() {
        return shouldExit;
    }

    /**
     * Performs the execution of the desired command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
