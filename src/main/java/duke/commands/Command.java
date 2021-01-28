package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Base command class to define relevant actions for each given user's command.
 */
public interface Command {
    /**
     * Executes the command given.
     * @param tasks list of tasks
     * @param ui command line interface
     * @param storage storage to handle file IO
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the program should end.
     * @return whether the program should end
     */
    public boolean isExit();
}
