package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Todo;

public class AddTodoCommand extends Command {

    private static final boolean toExit = false;

    private Todo todo;

    public AddTodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        tasks.addTask(this.todo);
        storage.save(tasks);

        String message = Ui.getAddTaskSuccess(this.todo);
        return new CommandResponse(AddTodoCommand.class,
                message, AddTodoCommand.toExit);
    }
}
