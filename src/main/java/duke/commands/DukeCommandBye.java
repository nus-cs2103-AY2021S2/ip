package duke.commands;

import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.TaskList;

/**
 * Exit command.
 *
 * Allows main loop to terminate program.
 */
public class DukeCommandBye extends DukeCommand {

    /**
     * Prints exit message.
     *
     * @param tasks tasklist
     * @param loader loader
     */
    @Override
    public Response execute(TaskList tasks, FileLoader loader) {
        return Response.createResponseExit();
    }
}
