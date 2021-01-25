package command;

import Exceptions.DukeException;
import Exceptions.UnknownInputException;
import ui.Ui;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;

public class AddToDoCommand extends Command{
    private final ToDo todo;

    public AddToDoCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new UnknownInputException("todo");
        }
        this.todo = new ToDo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(todo);
        ui.displayAddedTask(todo, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
