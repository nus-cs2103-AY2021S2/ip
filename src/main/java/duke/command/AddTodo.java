package duke.command;

import duke.task.Todo;

/**
 * Represents a command that adds Todo into the task list.
 */
public class AddTodo extends AddCommand {
    private Todo task;

    /**
     * Constructor for AddTodo class.
     * @param task Todo to be added.
     */
    public AddTodo(Todo task) {
        super(task);
    }
}
