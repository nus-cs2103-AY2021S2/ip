package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public abstract class Command {

    public abstract CommandResponse getResponse(TaskList tasks,
                                                Storage storage) throws DukeException;
}
