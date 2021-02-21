package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public abstract class Command {

    public abstract CommandResponse getResponse(TaskList tasks,
                                                Storage storage) throws DukeException;
}
