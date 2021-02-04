package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.models.Task;
import duke.models.Todo;
import duke.ui.Ui;

/**
 * Handles the Todo command of the user to create new todos in the list.
 * Format of command: "todo [todo_name]".
 */
public class TodoCommand implements Command {
    private String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curTask = new Todo(taskName);
        tasks.addTask(curTask);
        ui.printTaskListStatus(tasks, curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
