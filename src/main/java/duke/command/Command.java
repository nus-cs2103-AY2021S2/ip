package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public interface Command {
    /**
     * Executes the command
     *
     * @param taskList
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException;
}
