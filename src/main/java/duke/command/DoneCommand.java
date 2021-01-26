package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int listSize = taskList.size();
        if (listSize <= 0) {
            throw new DukeException("Your tasks list is empty.");
        }

        if (index < 0 || index >= listSize) {
            throw new DukeException("The index you entered is out of bound.");
        }

        Task task = taskList.get(index);
        if (task.getDone()) {
            throw new DukeException("You have already completed this task!");
        }

        task.setDone();
        ui.showDoneMessage(task);
        storage.saveFile(taskList);
    }
}
