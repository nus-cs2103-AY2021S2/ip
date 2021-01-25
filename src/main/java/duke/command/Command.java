package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public interface Command {
    void run(Storage storage, TaskList taskList) throws DukeException;
}
