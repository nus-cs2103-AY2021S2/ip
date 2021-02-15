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
     * Returns exit response.
     *
     * @param tasks tasklist
     * @param loader loader
     * @return Response
     */
    @Override
    public Response execute(TaskList tasks, FileLoader loader) {
        return Response.createResponseExit();
    }
}
