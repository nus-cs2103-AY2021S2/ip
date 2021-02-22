package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Todo;

/**
 * Represents the command to add a To-do task.
 */
public class AddTodoCommand extends Command {

    private static final boolean toExit = false;

    private Todo todo;

    /**
     * Constructs a AddTodoCommand object.
     *
     * @param description To-do description.
     */
    public AddTodoCommand(String description) {
        this.todo = new Todo(description);
    }

    /**
     * Executes the command and returns a response.
     *
     * @param tasks Core TaskList object.
     * @param storage Core Storage object.
     * @return CommandResponse object.
     * @throws QuackersException If unable to save data to disk.
     */
    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws QuackersException {
        tasks.addTask(this.todo);
        storage.save(tasks);

        String message = Ui.getAddTaskSuccess(this.todo);
        return new CommandResponse(AddTodoCommand.class, message, AddTodoCommand.toExit);
    }
}
