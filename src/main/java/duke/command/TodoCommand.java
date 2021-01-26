package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("Argument not specified!");
        }
        this.todo = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(todo);
        ui.printAddTaskAck(todo, tasks);
        storage.write(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
