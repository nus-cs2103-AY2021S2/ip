package moomin.command;

import moomin.exception.DukeException;
import moomin.storage.Storage;
import moomin.task.Task;
import moomin.task.TaskList;
import moomin.task.Todo;
import moomin.ui.Ui;

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
        storage.setMostRecentCommand(this);
        storage.saveTasksToFile(tasks);
        return ui.getAddTaskReport(todo, tasks);
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(tasks.getTaskCount() - 1);
        tasks.deleteTask(tasks.getTaskCount() - 1);
        storage.saveTasksToFile(tasks);
        return ui.getUndoAddTaskMessage(task, tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
