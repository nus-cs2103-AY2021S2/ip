package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    /**
     * Method to execute initialized command object
     * @param tasks list of tasks
     * @param ui UI required for conversation
     * @param storage Storage required for .txt file
     * @throws DukeException DukeException thrown when error occurs in command execution
     */
    public abstract ArrayList<String> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean indicating whether the command is meant for exiting the bot
     * @return exit status
     */
    public boolean isExit() {
        return false;
    }
}
