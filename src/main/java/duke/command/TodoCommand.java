package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a 'todo' command.
 * Adds a new todo to the task list.
 */
public class TodoCommand extends Command {

    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (getArguments().isBlank()) {
            throw new DukeException("I apologize, please input description for 'todo'.");
        } else {
            Task newTask = new Todo(getArguments());
            taskList.add(newTask);
            storage.addToFile(newTask);
            ui.print("Added to to-do list: \n" + newTask);
        }
    }
}
