package ronald.command;

import ronald.RonaldException;
import ronald.ui.Ui;
import ronald.storage.Storage;
import ronald.task.Task;
import ronald.task.TaskList;
import ronald.task.Todo;

/**
 * Class containing data and methods specific to a Todo command.
 */
public class TodoCommand extends Command {

    public TodoCommand(String[] command) {
        super.command = command;
    }

    /**
     * Creates Todo object from commands provided during initialisation of the TodoCommand object. Adds it to the
     * data file and prints the added Todo object.
     *
     * @throws RonaldException if arguments provided to the TodoCommand object are invalid.
     */
    @Override
    public void process() throws RonaldException {
        Task task = Todo.createTodo(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
