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
    private boolean exit;

    public Command() {
        this.exit = false;
    }

    public Command(boolean exit) {
        this.exit = exit;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * Performs the execution of the desired command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
