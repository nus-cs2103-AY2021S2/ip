package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.exception.TaskIndexOutOfBoundException;

public class DeleteCommand extends IndexCommand {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = getIndex();
        if (index < tasks.size()) {
            Task removingTask = tasks.get(index);
            tasks.remove(index);
            storage.updateInFile(tasks);
            System.out.println(" Following task is removed:");
            System.out.println("  " + removingTask);
            System.out.println(" Now you have " + tasks.size() + " tasks.");
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }

    }
}
