package duke.command;

import duke.exceptions.DukeException;
import duke.parser.CommandType;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    public DoneCommand(CommandType type, String command) {
        super(type, command);
    }

    @Override
    public Response execute(TaskList tasks) {
        try {
            int selection = this.getCommandSelection(tasks);

            Task taskDone = tasks.get(selection - 1);
            taskDone.markComplete();

            StringBuilder text = new StringBuilder();
            text.append("Nice! I've marked this task as done:\n");
            text.append(taskDone);

            return new Response(text.toString(), ResponseStatus.OK);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Response("☹ OOPS!!! Please tell me which task to delete :-(", ResponseStatus.ERROR);
        } catch (DukeException e) {
            return new Response(e.getMessage(), ResponseStatus.ERROR);
        }
    }
}
