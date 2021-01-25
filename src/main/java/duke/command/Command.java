package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * The Command interface provides an interface for all Command classes to implement.
 */
public interface Command {
    void run(Storage storage, TaskList taskList) throws DukeException;
}
