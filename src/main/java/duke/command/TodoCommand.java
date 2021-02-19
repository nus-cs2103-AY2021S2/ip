package duke.command;

import duke.exceptions.DukeException;
import duke.parser.CommandType;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class TodoCommand extends Command {

    public TodoCommand(CommandType type, String command) {
        super(type, command);
    }

    @Override
    public Response execute(TaskList tasks) {
        try {
            Task task = Task.parseTask(this.type, this.command);
            StringBuilder text = new StringBuilder();
            tasks.add(task);
            text.append("Got it. I've added this task:\n  added: ");
            text.append(task);
            text.append("\nNow you have ");
            text.append(tasks.size());
            text.append(" tasks in the list.");
            return new Response(text.toString(), ResponseStatus.OK);
        } catch (DukeException e) {
            return new Response(e.getMessage(), ResponseStatus.ERROR);
        }
    }
}
