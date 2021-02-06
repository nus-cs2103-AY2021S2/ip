package duke.command;

import duke.exceptions.DukeException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    private final int taskDeleteInt;
    public DeleteCommand(int i) {
        taskDeleteInt = i;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task deletedTask = dukeTaskList.deleteTask(taskDeleteInt);
            dukeUi.showTaskDeleted(deletedTask, dukeTaskList.getNumberOfTasks());
        } catch (DukeException e) {
            throw e;
        }
    }

    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task deletedTask = dukeTaskList.deleteTask(taskDeleteInt);
            return dukeUi.returnTaskDeleted(deletedTask, dukeTaskList.getNumberOfTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
}

