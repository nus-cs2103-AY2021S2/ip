package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    private Todo todo;

    public AddTodoCommand(String details) throws DukeException {
        if (details.isBlank()) {
            throw new DukeException("You can't add an empty todo!");
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
