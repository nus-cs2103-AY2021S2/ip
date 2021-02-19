package duke.command;

import duke.exceptions.DukeException;
import duke.parser.CommandType;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(CommandType type, String command) {
        super(type, command);
    }

    @Override
    public Response execute(TaskList tasks) {
        try {
            int selection = this.getCommandSelection(tasks);

            Task task = tasks.remove(selection - 1);

            StringBuilder text = new StringBuilder();
            text.append("Noted. I've removed this task:\n  ");
            text.append(task);
            text.append("\nNow you have ");
            text.append(tasks.size());
            text.append(" tasks in the list.");

            return new Response(text.toString(), ResponseStatus.OK);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new Response("☹ OOPS!!! Please tell me which task to delete :-(", ResponseStatus.ERROR);
        } catch (DukeException e) {
            return new Response(e.getMessage(), ResponseStatus.ERROR);
        }
    }

}
