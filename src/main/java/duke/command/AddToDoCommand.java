package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class AddToDoCommand extends Command {
    private final ToDo todo;

    public AddToDoCommand(String descriptionString) throws DukeException {
        if (descriptionString.isBlank()) {
            throw new UnknownInputException("todo");
        }

        this.todo = new ToDo(descriptionString);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.add(todo);
            String result = ui.displayAddedTask(todo, tasks.getNumOfTasks());
            storage.saveTasks(tasks.getTasks());
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
