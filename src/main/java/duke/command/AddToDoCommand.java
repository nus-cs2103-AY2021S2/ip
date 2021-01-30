package duke.command;

import duke.Exceptions.DukeException;
import duke.Exceptions.UnknownInputException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class AddToDoCommand extends Command {
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
