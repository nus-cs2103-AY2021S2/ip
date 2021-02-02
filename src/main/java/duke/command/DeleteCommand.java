package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a 'delete' command.
 * Deletes a specified task from the task list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        int delIndex = Integer.parseInt(getArguments()) - 1;
        Task toDelete = taskList.get(Integer.parseInt(getArguments()) - 1);
        taskList.remove(delIndex);
        storage.deleteFromFile(delIndex);
        return "Affirmative. The following task has been removed: \n" + toDelete;
    }
}
