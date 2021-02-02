package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Parent abstract class to represent a command.
 */
public abstract class Command {
    private String arguments;
    private boolean isExit;

    protected Command(String arguments) {
        this.arguments = arguments;
        this.isExit = false;
    }

    public void setExit(boolean exit) {
        this.isExit = exit;
    }

    public String getArguments() {
        return this.arguments;
    }

    /**
     * Executes the current command .
     * @param storage Storage to be used.
     * @param ui Ui to be used.
     * @param taskList taskList to be used.
     * @throws DukeException if there is an issue with the command.
     */
    public abstract String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
