package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * An abstract base class of executable commands.
 */
public abstract class Command {

    /**
     * Indicator used to differentiate exit command
     */
    private boolean shouldExit;

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
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
