package duke.command;

import duke.task.TodoTask;

public class TodoCommand extends AddCommand {
    public TodoCommand(final String content) {
        super(new TodoTask(content));
    }
}
