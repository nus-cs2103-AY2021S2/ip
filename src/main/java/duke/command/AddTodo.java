package duke.command;

import duke.task.Todo;

public class AddTodo extends AddCommand {
    private Todo task;
    public AddTodo(Todo task) {
        super(task);
    }
}
