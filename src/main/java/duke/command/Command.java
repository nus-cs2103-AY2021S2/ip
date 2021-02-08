package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.tasks.TaskList;

/**
 * Abstract command class
 */
public abstract class Command {

    /**
     * Boolean value for whether the task is an exit command.
     */
    protected boolean isExit;

    /**
     * Constructor method for command class.
     * @param isExit The boolean value for whether the task is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Abstract execute method
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object
     * @param storage Standard storage object
     * @throws DukeException if exception is thrown.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

}
