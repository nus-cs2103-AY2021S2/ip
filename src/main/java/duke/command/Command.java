package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command
     *
     * @param taskList
     * @param ui
     * @throws DukeException
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

    /**
     * Returns whether the current command is going to exit the programme
     *
     * @return boolean
     */
    public boolean isExit() {
        return isExit;
    }
}
