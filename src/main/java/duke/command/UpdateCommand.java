package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class UpdateCommand implements Command {
    private final ArrayList<String> arrOfDescriptionToChange;
    private final int taskToEdit;


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
