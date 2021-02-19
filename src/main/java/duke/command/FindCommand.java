package duke.command;

import duke.parser.CommandType;
import duke.response.Response;
import duke.response.ResponseStatus;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class FindCommand extends Command {

    public FindCommand(CommandType type, String command) {
        super(type, command);
    }

    @Override
    public Response execute(TaskList tasks) {
        String query = this.command.split(" ", 2)[1];

        StringBuilder text = new StringBuilder();
        text.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.contains(query)) {
                text.append(i + 1);
                text.append(".");
                text.append(task);
                if (i != tasks.size() - 1) {
                    text.append("\n");
                }
            }
        }
        return new Response(text.toString(), ResponseStatus.OK);
    }
}
