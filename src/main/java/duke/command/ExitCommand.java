package duke.command;

import duke.parser.CommandType;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.TaskList;
import javafx.application.Platform;

public class ExitCommand extends Command {

    public ExitCommand(CommandType type, String command) {
        super(type, command);
    }

    @Override
    public Response execute(TaskList tasks) {
        Platform.exit();
        System.exit(0);
        return new Response("", ResponseStatus.EXIT);
    }
}
