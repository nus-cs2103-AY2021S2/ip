package duke.command;

import duke.exceptions.DukeException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

/**
 * DoneCommand, which marks a task as done when executed
 */
public class DoneCommand implements Command {
    private final int taskDoneInt;

    /**
     * Constructor of DoneCommand
     * @param i index of task to mark as done
     */
    public DoneCommand(int i) {
        taskDoneInt = i;
    }

    /**
     * Executes the Done command, which marks given task as done
     * @param dukeTaskList give dukeTaskList
     * @param dukeUi give dukeUi
     * @param dukeStorage give dukeStorage
     */
    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task doneTask = dukeTaskList.checkTaskAsDone(taskDoneInt);
            dukeUi.showTaskDone(doneTask);
        } catch (DukeException e) {
            throw e;
        }
    }

    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task doneTask = dukeTaskList.checkTaskAsDone(taskDoneInt);
            return dukeUi.returnTaskDone(doneTask);
        } catch (DukeException e) {
            throw e;
        }
    }
}
