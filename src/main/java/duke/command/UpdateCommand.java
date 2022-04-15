package duke.command;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

/**
 * Update Command, which updates the descriptions of a given task when executed
 */

public class UpdateCommand implements Command {
    private final ArrayList<String> arrOfDescriptionToChange;
    private final int taskToEdit;

    /**
     * Default Constructor of Update Command
     *
     * @param taskToEdit Task object which is to be edited
     * @param descriptionToChange ArrayList of descriptions to change
     */
    public UpdateCommand(int taskToEdit, ArrayList<String> descriptionToChange) {
        this.arrOfDescriptionToChange = descriptionToChange;
        this.taskToEdit = taskToEdit;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task task = dukeTaskList.getTask(taskToEdit);
            task.changeDescription(arrOfDescriptionToChange);
            dukeUi.showChangedTask(task);
        } catch (TaskException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) throws DukeException {
        try {
            Task task = dukeTaskList.getTask(taskToEdit);
            task.changeDescription(arrOfDescriptionToChange);
            return dukeUi.returnChangedTask(task);
        } catch (TaskException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
