package duke.command;

import duke.exceptions.DukeException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

/**
 * Delete Command, which deletes a task from Duke's TaskList when executed
 */

public class DeleteCommand implements Command {
    private final int taskDeleteInt;

    /**
     * Constructor of DeleteCommand
     *
     * @param i Index of task to delete
     */
    public DeleteCommand(int i) {
        assert i >= 0 : " i has to be greater or equal to 0 as there are no negative indexes";
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

