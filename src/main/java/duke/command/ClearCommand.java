package duke.command;

import duke.parser.CommandType;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.TaskList;

public class ClearCommand extends Command {

    public ClearCommand(CommandType type, String command) {
        super(type, command);
    }

    @Override
    public Response execute(TaskList tasks) {
        tasks.clear();
        return new Response("The entire task list has been cleared!", ResponseStatus.OK);
    }
}
