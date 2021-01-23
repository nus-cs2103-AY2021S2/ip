package duke.command;

import duke.task.Task;
import duke.exception.DukeCommandException;

import java.util.List;

public class ListCommand extends Command {
    @Override
    public void execute() throws DukeCommandException {
        List<Task> tasks = taskManager.getTasks();
        ui.printTaskList(tasks);
    }
}
