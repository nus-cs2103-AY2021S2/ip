package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class AddTodoCommand extends Command {

    private static final Boolean toExit = false;

    private Todo todo;

    public AddTodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.todo);

        String message = ui.getAddTaskSuccess(this.todo);
        return new CommandResponse(message, AddTodoCommand.toExit);
    }
}
