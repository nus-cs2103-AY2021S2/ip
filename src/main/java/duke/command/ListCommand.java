package duke.command;

import duke.task.Task;
import duke.exception.DukeCommandException;

import java.util.List;

/** An executable command to list down all tracked tasks */
public class ListCommand extends Command {
    /**
     * Executes the List command to list out all tasks in the list
     * @throws DukeCommandException if there is an issue retrieving tasks from the list or printing it through the Ui
     */
    @Override
    public void execute() throws DukeCommandException {
        List<Task> tasks = taskManager.getTasks();
        ui.printTaskList(tasks);
    }
}
