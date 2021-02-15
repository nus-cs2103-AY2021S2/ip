package duke.commands;

import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.TaskList;

/**
 * Superclass for all commands.
 */
public abstract class DukeCommand {

    public abstract Response execute(TaskList tasks, FileLoader loader);
}
