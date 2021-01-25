package duke.command;

import duke.Ui;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TaskList;
import duke.DukeException;
import duke.storage.Storage;

public class TodoCommand extends Command {

    public TodoCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        Task task = Todo.createTodo(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
