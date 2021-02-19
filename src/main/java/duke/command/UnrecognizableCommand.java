package duke.command;

import duke.parser.CommandType;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.TaskList;

public class UnrecognizableCommand extends Command {
    public UnrecognizableCommand() {
        super(CommandType.UNRECOGNIZED, null);
    }

    @Override
    public Response execute(TaskList tasks) {
        return new Response("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", ResponseStatus.ERROR);
    }
}
