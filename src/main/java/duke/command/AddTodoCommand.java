package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    private Todo todo;

    private static final String EMPTY_TODO_ERROR_MESSAGE = "You can't add an empty todo!";

    public AddTodoCommand(String details) throws DukeException {
        if (details.isBlank()) {
            throw new DukeException(EMPTY_TODO_ERROR_MESSAGE);
        }
        this.todo = new Todo(details);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(todo);
        ui.printAddTaskReport(todo, tasks);
        storage.saveTasksToFile(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
