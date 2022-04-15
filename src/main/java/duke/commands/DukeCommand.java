package duke.commands;

import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.TaskList;

/**
 * Superclass for all commands.
 */
public abstract class DukeCommand {

    /** Must be overridden by commands performing action. */
    public abstract Response execute(TaskList tasks, FileLoader loader);
}
