package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Class containing data and methods specific to a Todo command
 */
public class TodoCommand extends Command {

    public TodoCommand(String[] command) {
        super.command = command;
    }

    /**
     * Creates Todo object from commands provided during initialisation of the TodoCommand object, adds it to the
     * data file and prints the added Todo object.
     *
     * @throws DukeException if arguments provided to the TodoCommand object are invalid
     */
    @Override
    public void process() throws DukeException {
        Task task = Todo.createTodo(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
