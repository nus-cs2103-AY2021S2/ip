package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class AddToDoCommand extends Command {
    private final ToDo todo;

    public AddToDoCommand(String description) throws DukeException {
        if (description.isBlank()) {
            throw new UnknownInputException("todo");
        }

        this.todo = new ToDo(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(todo);
        String result = ui.displayAddedTask(todo, tasks.getNumOfTasks());
        storage.saveTasks(tasks.getTasks());
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
