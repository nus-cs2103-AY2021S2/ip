package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        Task task = Deadline.createDeadline(command);
        TaskList.addTask(task);
    }
}
