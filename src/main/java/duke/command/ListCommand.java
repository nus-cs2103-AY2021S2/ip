package duke.command;

import java.util.List;

import duke.exception.DukeCommandException;
import duke.task.Task;

/** An executable command to list down all tracked tasks */
public class ListCommand extends Command {
    /**
     * Executes the List command to list out all tasks in the list and returns a response message
     *
     * @throws DukeCommandException if there is an issue retrieving tasks from the list or printing it through the Ui
     */
    @Override
    public String execute() throws DukeCommandException {
        List<Task> tasks = taskManager.getTasks();
        return ui.constructTaskList(tasks);
    }
}
