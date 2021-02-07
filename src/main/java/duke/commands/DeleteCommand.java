package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.NotExistingTaskException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String description) {
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ",2);
        int currentIndex = Integer.parseInt(inputList[1]) - 1;

        if (currentIndex > taskList.getSize() || currentIndex + 1 <= 0) {
            throw new NotExistingTaskException("test");
        } else {
            Task deletedTask = taskList.removeTask(currentIndex);
            return ui.deletedTask(deletedTask);
        }
    }

    @Override
    public boolean isEndOfProgram() {
        return false;
    }
}
