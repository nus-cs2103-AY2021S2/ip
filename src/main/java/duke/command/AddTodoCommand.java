package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    private static final String EMPTY_TODO_ERROR_MESSAGE = "You can't add an empty todo!";

    private Todo todo;

    public AddTodoCommand(String details) throws DukeException {
        if (details.isBlank()) {
            throw new DukeException(EMPTY_TODO_ERROR_MESSAGE);
        }
        assert !details.isEmpty() : EMPTY_TODO_ERROR_MESSAGE;
        this.todo = new Todo(details);
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(todo);
        storage.saveTasksToFile(tasks);
        return ui.getAddTaskReport(todo, tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
