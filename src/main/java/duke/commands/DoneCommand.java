package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.NotExistingTaskException;
import duke.exceptions.TaskDoneException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;
import duke.tasks.Task;

public class DoneCommand extends Command {
    public DoneCommand(String description) {
        super(description);
    }

    /**
     * Marks a task when it is completed
     *
     * @param taskList, the list of tasks
     * @param ui, the UI object
     * @param storage, the storage object
     * @return String messaged based on Done Command
     * @throws DukeException
     */

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);
        int currentIndex = Integer.parseInt(inputList[1]) - 1;

        if (currentIndex + 1 > taskList.getSize() || currentIndex + 1 <= 0) {
            throw new NotExistingTaskException("test");
        } else {
            Task taskItem = taskList.getTask(currentIndex);

            if (taskItem.getDoneStatus()) {
                throw new TaskDoneException(taskItem);
            } else {
                taskItem.markCompleted();
                return ui.taskDone(taskItem);
            }
        }
    }

    @Override
    public boolean isEndOfProgram() {
        return false;
    }
}
