package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.exception.TaskIndexOutOfBoundException;

public class DoneCommand extends IndexCommand {
    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = getIndex();
        if (index < tasks.size()) {
            Task completedTask = tasks.get(index);
            completedTask.complete();
            storage.updateInFile(tasks);
            System.out.println(" Marked. How cool is that?");
            System.out.println("  " + completedTask);
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }
}
