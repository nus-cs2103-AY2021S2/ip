package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.DukeException;
import duke.storage.Storage;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        Task task = Deadline.createDeadline(command);
        TaskList.addTask(task, Storage.getTasks());
        Ui.displayAddedTask(task, Storage.getTasks());
        Storage.updateDataFile();
    }
}
