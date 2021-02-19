package duke.command;

import duke.exceptions.DukeException;
import duke.parser.CommandType;
import duke.response.Response;
import duke.tasks.TaskList;

public abstract class Command {
    protected final CommandType type;
    protected final String command;

    protected Command(CommandType type, String command) {
        this.type = type;
        this.command = command;
    }

    public abstract Response execute(TaskList tasks);

    protected int getCommandSelection(TaskList tasks) throws DukeException {
        int selection = Integer.parseInt(this.command.split(" ")[1]);
        if (selection > tasks.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but there is no such task :-(");
        }
        return selection;
    }
}
