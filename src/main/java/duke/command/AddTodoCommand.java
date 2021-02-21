package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Todo;

public class AddTodoCommand extends Command {

    private static final boolean toExit = false;

    private Todo todo;

    public AddTodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        tasks.addTask(this.todo);

        String message = Ui.getAddTaskSuccess(this.todo);
        return new CommandResponse(AddTodoCommand.class,
                message, AddTodoCommand.toExit);
    }
}
