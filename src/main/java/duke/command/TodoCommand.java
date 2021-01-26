package duke.command;

import duke.task.TodoTask;

public class TodoCommand extends AddCommand {
    /**
     * Constructs a new todo command with the associated content, in a todo task.
     * @param content the content of the todo
     */
    public TodoCommand(final String content) {
        super(new TodoTask(content));
    }
}
