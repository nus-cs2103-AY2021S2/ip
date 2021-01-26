package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int listSize = taskList.size();

        if (listSize <= 0) {
            throw new DukeException("Your task list is empty.");
        }

        if (index < 0 || index >= listSize) {
            throw new DukeException("The number you have entered is out of bound.");
        }

        Task task = taskList.delete(index);
        ui.showDeleteMessage(task, taskList.size());
        storage.saveFile(taskList);
    }
}
